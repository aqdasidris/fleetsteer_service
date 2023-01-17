package job.repository

import io.ktor.server.application.*
import job.data.JobData

class JobRepository: IJobRepository {
   private val defaultJob=JobData(job_id = 1,name="Corn", job_description = "delivery of 500kg corn", payment = 10000.00, contact =986935468 )
   private val jobstorage:MutableList<JobData> = mutableListOf(defaultJob)
    override fun getData(job_id: Int): JobData? {
        return jobstorage.find { it.job_id==job_id }
    }


}