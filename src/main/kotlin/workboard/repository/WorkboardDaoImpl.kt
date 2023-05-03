package workboard.repository

import Employee.data.EmployeeEntity
import common.FleetSteerDatabase.dbQuery
import common.dao.EmployeeDao
import common.dao.JobDao
import common.dao.VehicleDao
import common.dao.WorkBoardDao
import job.data.JobEntity
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import vehicle.data.VehicleEntity
import vehicle.data.vehicle_table
import workboard.data.WorkBoardEntity
import workboard.data.workBoardTable
import java.lang.Exception

class WorkboardDaoImpl(val vehicleDao: VehicleDao, val jobDao: JobDao, val employeeDao: EmployeeDao):WorkBoardDao {
    fun build(workboard: Int, vehicleEntity: VehicleEntity, jobEntity: JobEntity, employeeEntity: EmployeeEntity, status:Boolean): WorkBoardEntity {
        return WorkBoardEntity(
            id = workboard,
            vehicle = vehicleEntity,
            job = jobEntity,
            employee = employeeEntity,
            status = status,
)
    }

    override suspend fun addWorkboard(id:Int, vehicle:Int ,jobEntity: Int,employeeEntity: Int,status: Boolean): Int {
        var result=-1
        dbQuery {
            try {
                result=insertWorkboard(id, vehicle, jobEntity, employeeEntity, status)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        return result

    }

    override suspend fun getAllWorkBoard(): List<WorkBoardEntity>? {
        return dbQuery{
          workBoardTable.selectAll().map {
                val vehicleId = it[workBoardTable.vehicle]
                val vehicle = vehicleDao.getVehiclebyId(vehicleId)
                val jobId=it[workBoardTable.job]
                val job=jobDao.getJobById(jobId)
                val employeeId=it[workBoardTable.employee]
                val employee=employeeDao.getEmployeeById(employeeId)


                build(vehicleEntity = vehicle!!.first(), jobEntity = job.first()!!, employeeEntity = employee!!.first(), workboard = it[workBoardTable.id], status = it[workBoardTable.status ])
            }
        }
    }

    override suspend fun getWorkboardById(workboardId: Int): List<WorkBoardEntity>? {
      return dbQuery {
          workBoardTable.select { workBoardTable.id eq workboardId }.map {
              val vehicleId = it[workBoardTable.vehicle]
              val vehicle = vehicleDao.getVehiclebyId(vehicleId)
              val jobId=it[workBoardTable.job]
              val job=jobDao.getJobById(jobId)
              val employeeId=it[workBoardTable.employee]
              val employee=employeeDao.getEmployeeById(employeeId)


              build(vehicleEntity = vehicle!!.first(), jobEntity = job.first()!!, employeeEntity = employee!!.first(), workboard = it[workBoardTable.id], status = it[workBoardTable.status ])
          }
       }
    }

    override suspend fun delete(workboard: WorkBoardEntity) {
        dbQuery {
            workBoardTable.deleteWhere { id eq workboard.id }
        }
    }

    suspend fun insertWorkboard(id:Int, vehicle:Int ,jobEntity: Int,employeeEntity: Int,status: Boolean):Int{
        val result=dbQuery {
            workBoardTable.insert {
                it[workBoardTable.id]=id
                it[job]=jobEntity
                it[workBoardTable.employee]=employeeEntity
                it[workBoardTable.vehicle]= vehicle
                it[workBoardTable.status]=status
            }
        }
        println("ADDED RESPONSE${result.resultedValues?.get(0)}")
        val id=result.resultedValues?.get(0)?.get(vehicle_table.vehicle_id)
        return id ?: -1
    }
}