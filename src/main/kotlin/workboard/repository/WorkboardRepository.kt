package workboard.repository

import common.dao.WorkBoardDao
import workboard.data.WorkBoardEntity

class WorkboardRepository(val dao:WorkBoardDao):IWorkboardRepository {
    override suspend fun getAllWorkboard(): List<WorkBoardEntity>? {
        return dao.getAllWorkBoard() ?: emptyList()
    }

    override suspend fun getWorkboardById(id: Int): WorkBoardEntity? {
        return dao.getWorkboardById(id)?.first()
    }

    override suspend fun deleteWorkboard(workBoardEntity: WorkBoardEntity) {
        dao.delete(workBoardEntity)
    }

    override suspend fun addWorkboard(
        id: Int,
        vehicle: Int,
        jobEntity: Int,
        employeeEntity: Int,
        status: Boolean
    ): Int {
        return dao.addWorkboard(id, vehicle, jobEntity, employeeEntity, status)
    }
}