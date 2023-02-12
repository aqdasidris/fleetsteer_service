package job.usecase

import job.data.JobEntity

typealias Error = String
interface IJobUsecase {
   suspend fun getJobData(userId:Long): List<JobEntity?>
   suspend fun addJob(jobData:JobEntity):Pair<Boolean, Error?>
}