package workboard.usecase

import workboard.data.WorkBoardEntity
import workboard.repository.IWorkboardRepository
import workboard.repository.WorkboardRepository
import java.lang.Exception

class AddWorkboardUsecase(val workboardRepository: IWorkboardRepository): IAddWorkboardUsecase {
    override suspend fun addWorkboard(
        vehicle: Int,
        jobEntity: Int,
        employeeEntity: Int,
        status: Boolean
    ): AddWorkboardResult {
        var result:AddWorkboardResult=AddWorkboardResult.Failed("")
        try {
            val workBoardid=workboardRepository.addWorkboard(id = -1,vehicle, jobEntity, employeeEntity, status)
            if (workBoardid>=0){
                result=AddWorkboardResult.Success(workBoardid)
            }
        }catch (e:IllegalArgumentException){
            result=AddWorkboardResult.Failed("${e.message}")
        }
        return result
    }
}