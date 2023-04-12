package job.usecase

import job.data.JobEntity

typealias Error = String
interface IJobUsecase {
   suspend fun getJobData(userId:Long): List<JobEntity?>
   suspend fun getAllJobs():List<JobEntity?>
   suspend fun getJobById(jobId: Int): JobEntity?
   suspend fun deleteJob(job: JobEntity)

}