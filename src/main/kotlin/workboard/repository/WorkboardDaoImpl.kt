package workboard.repository

import Employee.data.EmployeeEntity
import common.dao.WorkBoardDao
import job.data.JobEntity
import org.jetbrains.exposed.sql.ResultRow
import vehicle.data.VehicleEntity
import vehicle.data.vehicle_table
import workboard.data.WorkBoardEntity
import workboard.data.workBoardTable

class WorkboardDaoImpl:WorkBoardDao {
    fun resultRowToWorkboard(row: ResultRow): WorkBoardEntity {
        WorkBoardEntity(
            id = row[workBoardTable.id],
            vehicle = row[workBoardTable.vehicle],
            job = row[workBoardTable.job],
            price = row[workBoardTable.price],
            employee = row[workBoardTable.employee],
            status = row[workBoardTable.status],
)
    }

    override suspend fun addWorkboard(workboard: WorkBoardEntity): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWorkBoard(): List<WorkBoardEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkboardById(workboardId: Int): List<WorkBoardEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun delete(workboard: WorkBoardEntity) {
        TODO("Not yet implemented")
    }
}