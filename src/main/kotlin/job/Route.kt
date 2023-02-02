package job

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import job.data.JobEntity

fun Route.jobRoute(){
        get("/job/{job_id}") {
            val id=call.parameters["job_id"]
            val jobinfo= jobsInfo()
            val jobs:JobEntity=jobinfo.find { it.job_id==id!!.toInt() }!!
            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
            call.respond(jobs)
        }

}
public fun jobsInfo():MutableList<JobEntity>{
    val job= mutableListOf<JobEntity>()
    job.addAll(
        arrayOf(
            JobEntity(job_id = 2, name = "corn", job_description ="500kg of corn to be delivered", payment = 5000.00, contact = 989839284)
        )
    )
    return job
}

