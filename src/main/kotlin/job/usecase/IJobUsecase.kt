package job.usecase

import job.data.JobData

interface IJobUsecase {
    fun getJobData(job_id:Int): JobData
}