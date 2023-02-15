package data.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class AuthCredentials(val username:String, val password: String)

object AuthCredential:Table(){
    val username=varchar("username",40)
    val password=varchar("password",40)
}
