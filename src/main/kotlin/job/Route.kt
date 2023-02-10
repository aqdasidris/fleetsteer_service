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

fun Route.jobRoute(){
        get("/job/{user_id}") {
            val id=call.parameters["user_id"]
            val jobinfo= jobsInfo()
            val jobs:JobEntity=jobinfo.find { it.userId==id!!.toLong() }!!
            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
            call.respond(jobs)
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
public fun jobsInfo():MutableList<JobEntity>{
    val job= mutableListOf<JobEntity>()
    job.addAll(
        arrayOf(
            JobEntity(job_id = 2, name = "corn", job_description ="500kg of corn to be delivered", payment = 5000.00, contact = 989839284, userId = 1)
        )
    )
    return job
}

