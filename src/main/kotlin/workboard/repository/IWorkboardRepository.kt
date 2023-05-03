package workboard.repository

import workboard.data.WorkBoardEntity

interface IWorkboardRepository {

    suspend fun getAllWorkboard():List<WorkBoardEntity>?

    suspend fun getWorkboardById(id:Int):WorkBoardEntity?

    suspend fun deleteWorkboard(workBoardEntity: WorkBoardEntity)
    suspend fun addWorkboard(id:Int, vehicle:Int ,jobEntity: Int,employeeEntity: Int,status: Boolean):Int
}