package job

import common.dao.JobDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import job.data.JobEntity
import job.repository.JobDaoImpl
import job.repository.JobRepository
import job.usecase.AddJobResult
import job.usecase.AddJobUsecase
import job.usecase.JobUsecase
import kotlinx.coroutines.runBlocking
import java.util.logging.Logger
val jobDao=JobDaoImpl().apply { runBlocking {  } }
val repository=JobRepository(jobDao)
val jobUsecase=JobUsecase(repository)
val addJobUsecase=AddJobUsecase(repository)
fun Route.jobRoute(){
        get("/job/{user_id}") {
            val id=call.parameters["user_id"]
            val jobinfo= jobsInfo(id!!.toLong())
            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
            call.respond(jobinfo)
        }
    get("/job") {
        val job= jobUsecase.getAllJobs()
        call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
        call.respond(job)
    }
        post("/job") {
            val job=call.receive<JobEntity>()
            val result=addJobUsecase.addJob(job)
            when(result){
                is AddJobResult.Failed -> call.response.status(HttpStatusCode.BadRequest)
                is AddJobResult.Success -> {
                    val id=result.jobId
                    val insertedJob=job.copy(job_id = id)
                    call.respond(status = HttpStatusCode.Created,insertedJob)
                }
            }

        }
        delete("/job") {
            val id=call.parameters["job_id"]?.toInt() ?: -1
            if(id>=0){
                val job = jobUsecase.getJobById(jobId = id)
                job?.let {
                    jobUsecase.deleteJob(job)
                    call.respond(status = HttpStatusCode.OK, job)
                } ?: call.respond(HttpStatusCode.OK)
            }else{
                call.response.status(HttpStatusCode.BadRequest)
            }
        }

}
public suspend fun jobsInfo(userId:Long):List<JobEntity?>{
    val job=jobUsecase.getJobData(userId)
    return job

}



