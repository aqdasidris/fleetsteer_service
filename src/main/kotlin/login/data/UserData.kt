package login.data

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class UserData(val userID: Long, val username: String, val password: String, val usertype: String)

object Userdata: Table(){
    val userID=long("userID").autoIncrement()
    val username= varchar("username",20).uniqueIndex()
    val password=varchar("password",50)
    val usertype=varchar("usertype",1)
    override val primaryKey=PrimaryKey(userID)
}
