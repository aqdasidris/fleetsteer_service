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
        post("/job/add") {
            val job=call.receive<JobEntity>()
            addJobUsecase.addJob(job)
            call.respondText("added sucessfully", status = HttpStatusCode.Created)
            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
        }

}
public suspend fun jobsInfo(userId:Long):List<JobEntity?>{
    val job=jobUsecase.getJobData(userId)
    return job

}



