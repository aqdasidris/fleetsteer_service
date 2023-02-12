package job.repository

import job.data.JobEntity

interface IJobRepository {
    fun getData(userId:Long): JobEntity?
    fun addJob(jobData: JobEntity)
}