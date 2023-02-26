package job.repository

import job.data.JobEntity

interface IJobRepository {
    suspend fun getData(userId:Long):List<JobEntity?>
    suspend fun addJob(jobData: JobEntity):Int
    suspend fun getJobById(jobId: Int): JobEntity?
    suspend fun deleteJob(job: JobEntity)


}