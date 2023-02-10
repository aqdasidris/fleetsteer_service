package job.usecase

import job.data.JobEntity

typealias Error = String
interface IJobUsecase {
    fun getJobData(job_id:Int): JobEntity
    fun addJob(jobData:JobEntity):Pair<Boolean, Error?>
}