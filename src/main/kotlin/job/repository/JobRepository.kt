package job.repository

import job.data.JobEntity

class JobRepository: IJobRepository {
   private val defaultJob=JobEntity(job_id = 1,name="Corn", job_description = "delivery of 500kg corn", payment = 10000.00, contact =986935468, userId = 1 )
   private val jobstorage:MutableList<JobEntity> = mutableListOf(defaultJob)
    override fun getData(job_id: Int): JobEntity? {
        return jobstorage.find { it.job_id==job_id }
    }

    override fun addJob(jobData: JobEntity) {
        val existingJob=jobstorage.find { it.job_id == jobData.job_id || it.userId==jobData.userId }
        if(existingJob!=null){
            throw IllegalArgumentException("The job already exists")
        }
        jobstorage.add(jobData)
    }


}