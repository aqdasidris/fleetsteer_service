package login.data

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class UserData(val userID: Long, val username: String, val password: String, val usertype: String)

object UserDataTable: Table(){
    val userID=long("userid").autoIncrement()
    val username= varchar("username",20).uniqueIndex()
    val password=varchar("password",50)
    val usertype=varchar("usertype", 10)
    override val primaryKey=PrimaryKey(userID)
}
