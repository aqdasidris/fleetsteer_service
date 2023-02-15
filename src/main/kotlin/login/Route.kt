package login

import CustomUserIdPrinciple
import data.model.MemberData
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Route.loginRoute() {
    authenticate("auth-basic") {
        post("/login") {
            val uID: Long? = call.principal<CustomUserIdPrinciple>()?.uID
            val type: String? = call.principal<CustomUserIdPrinciple>()?.type

            if (type == null || uID == null) {
                call.respond(HttpStatusCode.Unauthorized, "invalid username or password")
            }else{
                uID.let {
                    type.let {
                        call.respond(MemberData(uID, type))
                    }
                }
            }
        }

    }
}