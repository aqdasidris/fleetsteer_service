package job

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import job.data.JobEntity
import job.repository.JobRepository
import job.usecase.JobUsecase
import java.util.logging.Logger

fun Route.jobRoute(){
        get("/job/{user_id}") {
            val id=call.parameters["user_id"]
            val jobinfo= jobsInfo(id!!.toLong())
            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
            call.respond(jobinfo)
        }
        post("/job/add") {
            val repository=JobRepository()
            val jobAddUsecase=JobUsecase(repository)
            val job=call.receive<JobEntity>()
            jobAddUsecase.addJob(job)

            call.respondText("added sucessfully", status = HttpStatusCode.Created)
            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
            print(job)
        }

}
public fun jobsInfo(userId:Long):JobEntity{
    val repository=JobRepository()
    val jobUsecase=JobUsecase(repository)
    val job=jobUsecase.getJobData(userId)
    return job

}

