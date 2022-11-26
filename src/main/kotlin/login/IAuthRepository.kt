package login

import kotlinx.serialization.Serializable

interface IAuthRepository {
    fun getUID(username: String): Long?
    fun insertNewUser(userData: UserData)
    fun getUserData(uID: Long): UserData?

    @Serializable
    data class UserData(val uID: Long, val username: String, val password: String, val usertype: String)

}