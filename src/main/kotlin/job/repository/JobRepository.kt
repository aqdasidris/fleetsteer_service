package job.repository

import common.dao.JobDao
import job.data.JobEntities
import job.data.JobEntity

class JobRepository(val jobDao: JobDao): IJobRepository {


    override suspend fun getData(userId: Long): List<JobEntity?> {
        return jobDao.getJob(userId)
    }

    override suspend fun addJob(jobData: JobEntity) {
        val existingJob=jobDao.getJob(jobData.userId)
        if(existingJob!=null){
            throw IllegalArgumentException("The job already exists")
        }
        jobDao.addJob(jobData)
    }


}