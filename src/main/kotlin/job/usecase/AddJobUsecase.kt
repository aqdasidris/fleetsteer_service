package job.usecase

import job.data.JobEntity
import job.repository.JobRepository
import javax.inject.Inject

class AddJobUsecase @Inject constructor(val repository: JobRepository):IAddJobUsecase {
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
}