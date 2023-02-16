package login.repository

import kotlinx.serialization.Serializable
import login.data.UserData
import org.jetbrains.exposed.sql.Table

interface IAuthRepository {
    suspend fun getUID(username: String): Long?
    suspend fun insertNewUser(userData: UserData)
    suspend fun getUserData(uID: Long): UserData?


    suspend fun newUserID():Long?



}