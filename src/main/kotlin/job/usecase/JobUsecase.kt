package job.usecase

import job.data.JobEntity
import job.repository.IJobRepository


class JobUsecase (private val repository: IJobRepository):IJobUsecase {
    override suspend fun getJobData(userId: Long): List<JobEntity?> {
        return repository.getData(userId)
    }

    override suspend fun getAllJobs(): List<JobEntity?> {
        return repository.getAllJobs()
    }

    override suspend fun getJobById(jobId: Int): JobEntity? {
        return repository.getJobById(jobId)
    }

    override suspend fun deleteJob(job: JobEntity) {
        repository.deleteJob(job)
    }


//    private fun JobEntity.toDomain():JobEntity{
//        return JobEntity(job_id = this.job_id, name = this.name,job_description=this.job_description, payment = this.payment, contact = this.contact, userId=this.userId)
//    }

}