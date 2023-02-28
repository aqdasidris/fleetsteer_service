package vehicle.repository

import common.FleetSteerDatabase.dbQuery
import common.dao.VehicleDao
import io.ktor.server.plugins.*
import job.data.JobEntities
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import vehicle.data.VehicleEntity
import vehicle.data.vehicle_table

class VehicleDaoImpl:VehicleDao {

    private fun resultRowToVehicleEntity(row: ResultRow):VehicleEntity{
        return VehicleEntity(
            vehicleId = row[vehicle_table.vehicle_id],
            vehicleName = row[vehicle_table.vehicle_name],
            vehicleNumber = row[vehicle_table.vehicle_number],
            vehicleType = row[vehicle_table.vehicle_type],
            vehicleDriverId = row[vehicle_table.vehicle_driver_id],
            vehicleAvailability = row[vehicle_table.vehicle_availability]
        )
    }
    override suspend fun addVehicle(vehicleData: VehicleEntity): Int {
        var result=-1
        dbQuery {
            try {
                result=insertVehicle(vehicleData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return result
    }

    private suspend fun insertVehicle(vehicleData: VehicleEntity):Int{
        val result=dbQuery{
            vehicle_table.insert {
                //it[vehicle_id]=vehicleData.vehicleId
                it[vehicle_name]=vehicleData.vehicleName
                it[vehicle_number]=vehicleData.vehicleNumber
                it[vehicle_type]=vehicleData.vehicleType
                it[vehicle_driver_id]=vehicleData.vehicleDriverId
                it[vehicle_availability]=vehicleData.vehicleAvailability
            }
        }
        println("ADDED RESPONSE${result.resultedValues?.get(0)}")
        val id=result.resultedValues?.get(0)?.get(vehicle_table.vehicle_id)
        return id ?: -1
    }

    private suspend fun selectVehicleById(vehicleId:Int):List<VehicleEntity>? = dbQuery{
        val vehicleByid=vehicle_table.select { vehicle_table.vehicle_id eq vehicleId }.map {
            VehicleEntity(
                vehicleId = it[vehicle_table.vehicle_id] ,
                vehicleName = it[vehicle_table.vehicle_name],
                vehicleNumber = it[vehicle_table.vehicle_number],
                vehicleType = it[vehicle_table.vehicle_type],
                vehicleDriverId = it[vehicle_table.vehicle_driver_id],
                vehicleAvailability =it[vehicle_table.vehicle_availability]
            )
        }
        return@dbQuery vehicleByid
    }
    override suspend fun getAllVehicleData(): List<VehicleEntity>? = dbQuery {
        vehicle_table.selectAll().map(::resultRowToVehicleEntity)
    }

    override suspend fun getVehiclebyId(vehicleId: Int): List<VehicleEntity>? {
        try {
            return selectVehicleById(vehicleId)
        }catch (e:NotFoundException){
            println("vehicle not found: $e")
        }
        return emptyList()
    }

    override suspend fun delete(vehicleEntity: VehicleEntity) {
        dbQuery { vehicle_table.deleteWhere { vehicle_table.vehicle_id eq vehicleEntity.vehicleId } }
    }


}