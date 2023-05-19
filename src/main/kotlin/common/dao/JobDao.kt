package common.dao

import job.data.JobEntities
import job.data.JobEntity

interface JobDao {
    suspend fun getJob(userId:Long):List<JobEntity?>
    suspend fun addJob(jobData:JobEntity):Int
    suspend fun getAllJobs():List<JobEntity?>
    suspend fun getJobById(id:Int):List<JobEntity?>
    suspend fun delete(job: JobEntity)
}