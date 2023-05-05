package workboard.usecase

import workboard.data.WorkBoardEntity

interface IWorkboardUsecase {

    suspend fun getAllWorkboard():List<WorkBoardEntity>?

    suspend fun getWorkboardById(id:Int): WorkBoardEntity?

    suspend fun deleteWorkboard(workBoardEntity: WorkBoardEntity)
}