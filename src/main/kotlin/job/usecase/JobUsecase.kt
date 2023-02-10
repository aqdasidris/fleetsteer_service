package job.usecase

import job.data.JobEntity
import job.repository.IJobRepository

class JobUsecase(private val repository: IJobRepository):IJobUsecase {
    override fun getJobData(job_id: Int): JobEntity {
        return repository.getData(job_id)?.toDomain()?: throw IllegalArgumentException("Job id not found")
    }

    private fun JobEntity.toDomain():JobEntity{
        return JobEntity(job_id = this.job_id, name = this.name,job_description=this.job_description, payment = this.payment, contact = this.contact, userId=this.userId)
    }
}