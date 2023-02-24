import common.FleetSteerDatabase
import job.data.JobStatus
import data.model.MemberData
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.date.*
import job.jobRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import login.usecase.AuthenticationUsecase
import login.repository.IAuthRepository
import login.loginRoute
import login.repository.LoginAuthRepository
import login.repository.LoginDaoImpl
import login.usecase.CreateFirstAdminUsecase
import login.usecase.CreateUserUsecase
import org.slf4j.event.Level

fun main(args: Array<String>):Unit = io.ktor.server.netty.EngineMain.main(args)

@Serializable
data class CustomUserIdPrinciple(val uID: Long, val type: String) : Principal

fun Application.module() {
    FleetSteerDatabase.init()
    val loginDao = LoginDaoImpl()
    val repository: IAuthRepository=LoginAuthRepository(loginDao)
    val authenticator = AuthenticationUsecase(repository)
    val createUserUseCase = CreateUserUsecase(repository)
    val createAdminUseCase = CreateFirstAdminUsecase(createUserUseCase, repository)

    install(CallLogging) {
        level = Level.INFO
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    val jobdetail = jobs()
    val memberData= mutableListOf<MemberData>()
    memberData.addAll(
        arrayOf(
            MemberData(id=0, type="Admin"),
            MemberData(id=1,"Manager"),
            MemberData(id=2, "Driver")
        )
    )
    install(Authentication){
        basic("auth-basic") {
            realm="access to the '/login' path"
            validate{authCredentials->
                val isValidUser = authenticator.isUserValid(authCredentials.name, authCredentials.password)
                if(isValidUser.first){
                    val uID = isValidUser.second
                    uID?.let {
                        val userData = authenticator.getUserData(uID)
                        return@validate CustomUserIdPrinciple(userData.uID, userData.type.name)
                    }

                }
                null

            }
        }
    }

    routing {
        get("/") {
            println("/: Creating Admin")
            createAdminUseCase.invoke()
            call.respondText("Setting Up Environment", status = HttpStatusCode.Created)
            println("admin added")
        }

//        get("/job/{driver_id}") {
//            val id = call.parameters["driver_id"]
//            ContentType.Application.Json
//            val joblist: JobStatus = jobdetail.find { it.driver_id == id!!.toString() }!!
//            call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
//            call.respond(joblist)
//        }
        loginRoute()
        jobRoute()
//        post("/job") {
//            val job = call.receive<Job>()
//            jobdetail.add(job)
//            call.respondText("job stored correctly ", status = HttpStatusCode.Created)
//        }
    }
}

private fun Application.jobs(): MutableList<JobStatus> {
    val jobdetail = mutableListOf<JobStatus>()
    jobdetail.addAll(
        arrayOf(
            JobStatus("1", "2124D", "51353515", "468646", "453353", "4543453", estimate_time = getTimeMillis())
        )
    )
    return jobdetail
}
