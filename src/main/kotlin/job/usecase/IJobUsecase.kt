package job.usecase

import job.data.JobEntity

interface IJobUsecase {
    fun getJobData(job_id:Int): JobEntity
}