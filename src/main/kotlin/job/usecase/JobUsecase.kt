package job.usecase

import job.data.JobData
import job.repository.IJobRepository
import job.repository.JobRepository

class JobUsecase(private val repository: IJobRepository):IJobUsecase {
    override fun getJobData(job_id: Int): JobData {
        return repository.getData(job_id)?.toDomain()?: throw IllegalArgumentException("Job id not found")
    }

    private fun JobData.toDomain():JobData{
        return JobData(job_id = this.job_id, name = this.name,job_description=this.job_description, payment = this.payment, contact = this.contact)
    }
}