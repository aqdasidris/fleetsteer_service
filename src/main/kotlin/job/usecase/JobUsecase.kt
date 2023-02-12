package job.usecase

import job.data.JobEntity
import job.repository.IJobRepository

class JobUsecase(private val repository: IJobRepository):IJobUsecase {
    override suspend fun getJobData(userId: Long): List<JobEntity?> {
        return repository.getData(userId)
    }

    override suspend fun addJob(jobData: JobEntity): Pair<Boolean, Error?> {
        val  existingJob=repository.getData(jobData.userId)
        if(existingJob!=null){
            return Pair(false,"job already Exists")
        }

        val newJobData=JobEntity(job_id = jobData.job_id, name = jobData.name, job_description = jobData.job_description, delivery_address = jobData.delivery_address, payment = jobData.payment, contact = jobData.contact, userId = jobData.userId)
        try{
            repository.addJob(newJobData)
        }catch (e:java.lang.IllegalArgumentException){
          return  Pair(false,"something went wrong")
        }
        return Pair(true,null)
    }



//    private fun JobEntity.toDomain():JobEntity{
//        return JobEntity(job_id = this.job_id, name = this.name,job_description=this.job_description, payment = this.payment, contact = this.contact, userId=this.userId)
//    }

}