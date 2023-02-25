package job.usecase

import job.data.JobEntity

interface IAddJobUsecase {
    suspend fun addJob(jobData: JobEntity): AddJobResult

}

sealed class AddJobResult{
    data class Success(val jobId:Int):AddJobResult()
    data class Failed(val error:String):AddJobResult()
}