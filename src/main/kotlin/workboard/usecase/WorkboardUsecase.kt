package workboard.usecase

import workboard.data.WorkBoardEntity
import workboard.repository.IWorkboardRepository

class WorkboardUsecase(val repository: IWorkboardRepository):IWorkboardUsecase {
    override suspend fun getAllWorkboard(): List<WorkBoardEntity>? {
        return repository.getAllWorkboard()
    }

    override suspend fun getWorkboardById(id: Int): WorkBoardEntity? {
        return repository.getWorkboardById(id)
    }

    override suspend fun deleteWorkboard(workBoardEntity: WorkBoardEntity) {
        return repository.deleteWorkboard(workBoardEntity)
    }
}