package login.usecase.data

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table


@Serializable
data class AuthUserData(val uID: Long, val username: String, val type: UserType)

//object AuthUserdata:Table(){
//    val uID=long("uID")
//    val username=varchar("username",40)
//    val type= varchar("type",40)
//}

enum class UserType{
    Manager, Driver, Admin
}
