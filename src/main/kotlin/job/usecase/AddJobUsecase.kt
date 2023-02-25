package job.usecase

import job.data.JobEntity
import job.repository.JobRepository


class AddJobUsecase(val repository: JobRepository):IAddJobUsecase {
    override suspend fun addJob(jobData: JobEntity): AddJobResult {
//        val  existingJob=repository.getData(jobData.userId)
//        if(existingJob.isNotEmpty()){
//            return Pair(false,"job already Exists")
//        }
        var result:AddJobResult=AddJobResult.Failed(error = "")
        val newJobData=JobEntity(job_id = -1, name = jobData.name, job_description = jobData.job_description, pickup_address = jobData.pickup_address, delivery_address = jobData.delivery_address, payment = jobData.payment, contact = jobData.contact, userId = jobData.userId)
        try{

           val jobId=repository.addJob(newJobData)
            if (jobId>=0){
                result=AddJobResult.Success(jobId)
            }
        }catch (e:java.lang.IllegalArgumentException){

            result=AddJobResult.Failed(e.message?: e.toString())
        }
        return result
    }


}