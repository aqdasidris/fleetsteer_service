package workboard.usecase

interface IAddWorkboardUsecase {
    suspend fun addWorkboard( vehicle:Int ,jobEntity: Int,employeeEntity: Int,status: Boolean):AddWorkboardResult
}

sealed class AddWorkboardResult{
    data class Success(val workboardID:Int):AddWorkboardResult()
    data class Failed(val error:String):AddWorkboardResult()
}