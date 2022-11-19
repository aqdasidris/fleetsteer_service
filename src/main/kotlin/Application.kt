import data.model.Job
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import io.ktor.util.date.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

fun main(args: Array<String>):Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
    }
    val jobdetail = mutableListOf<Job>()
    jobdetail.addAll(
        arrayOf(
            Job("1", "2124D", "51353515", "468646", "453353", "4543453", estimate_time = getTimeMillis())
        )
    )

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    routing {
        get("/job/{driver_id}") {
            val id = call.parameters["driver_id"]
            ContentType.Application.Json
            val joblist: Job = jobdetail.find { it.driver_id == id!!.toString() }!!
            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
            call.respond(joblist)
        }
//        post("/job") {
//            val job = call.receive<Job>()
//            jobdetail.add(job)
//            call.respondText("job stored correctly ", status = HttpStatusCode.Created)
//        }
    }
}
