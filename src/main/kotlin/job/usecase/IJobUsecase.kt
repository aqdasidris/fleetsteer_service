package job.usecase

import job.data.JobEntity

typealias Error = String
interface IJobUsecase {
    fun getJobData(userId:Long): JobEntity
    fun addJob(jobData:JobEntity):Pair<Boolean, Error?>
}