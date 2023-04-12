package job.repository

import common.dao.JobDao
import job.data.JobEntities
import job.data.JobEntity

class JobRepository(val jobDao: JobDao): IJobRepository {


    override suspend fun getData(userId: Long): List<JobEntity?> {
        return jobDao.getJob(userId)
    }

    override suspend fun getAllJobs(): List<JobEntity?> {
        return jobDao.getAllJobs()
    }

    override suspend fun addJob(jobData: JobEntity): Int {
//        val existingJob=jobDao.getJob(jobData.userId)
//        if(existingJob!=null){
//            throw IllegalArgumentException("The job already exists")
//        }
        return jobDao.addJob(jobData)
    }

    override suspend fun getJobById(jobId: Int): JobEntity? {
        return jobDao.getJobById(jobId).first()
    }

    override suspend fun deleteJob(job: JobEntity) {
        jobDao.delete(job)
    }


}