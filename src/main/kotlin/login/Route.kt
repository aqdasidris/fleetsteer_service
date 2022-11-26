package login

import CustomUserIdPrinciple
import data.model.MemberData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.loginRoute() {
    authenticate("auth-basic") {
        post("/login") {
            ContentType.Application.Json

            val uID: Long? = call.principal<CustomUserIdPrinciple>()?.uID
            val type: String? = call.principal<CustomUserIdPrinciple>()?.type
            if (type == null || uID == null) {
                call.respond(HttpStatusCode.Unauthorized, "invalid username or password")
                return@post
            }

            uID?.let {
                type?.let {
                    call.respond(MemberData(uID, type))
                }
            }

        }
    }

}