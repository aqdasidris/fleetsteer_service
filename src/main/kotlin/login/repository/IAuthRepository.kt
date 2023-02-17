package login.repository

import login.data.UserData

interface IAuthRepository {
    suspend fun getUID(username: String): Long?
    suspend fun insertNewUser(userData: UserData)
    suspend fun getUserData(uID: Long): UserData?


}