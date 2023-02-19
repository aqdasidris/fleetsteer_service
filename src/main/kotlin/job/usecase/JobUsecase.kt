package job.usecase

import job.data.JobEntity
import job.repository.IJobRepository
import javax.inject.Inject

class JobUsecase @Inject constructor(private val repository: IJobRepository):IJobUsecase {
    override suspend fun getJobData(userId: Long): List<JobEntity?> {
        return repository.getData(userId)
    }





//    private fun JobEntity.toDomain():JobEntity{
//        return JobEntity(job_id = this.job_id, name = this.name,job_description=this.job_description, payment = this.payment, contact = this.contact, userId=this.userId)
//    }

}