package common.dao

import job.data.JobEntities
import job.data.JobEntity

interface JobDao {
    suspend fun getJob(userId:Long):List<JobEntity?>
    suspend fun addJob(jobData:JobEntity):JobEntities?
    suspend fun getAllJobs():List<JobEntity?>
}