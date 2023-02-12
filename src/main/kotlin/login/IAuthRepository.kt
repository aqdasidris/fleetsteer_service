package login

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.stringLiteral

interface IAuthRepository {
    fun getUID(username: String): Long?
    fun insertNewUser(userData: UserData)
    fun getUserData(uID: Long): UserData?

    @Serializable
    data class UserData(val uID: Long, val username: String, val password: String, val usertype: String)

    object Userdata: Table(){
        val uID=long("uID").autoIncrement()
        val username= varchar("username",20).uniqueIndex()
        val password=varchar("password",50)
        val usertype=varchar("usertype",1)
        override val primaryKey=PrimaryKey(uID)


    }

}