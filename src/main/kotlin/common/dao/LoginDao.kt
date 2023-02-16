package common.dao

import login.data.UserData
import login.repository.IAuthRepository

interface LoginDao {
    suspend fun getUid(username:String):Long?
    suspend fun insertNewUser(userData: UserData):UserData?
    suspend fun getUserData(uId:Long):UserData?
    suspend fun getAllUsers():List<UserData?>
    suspend fun generateId(): Long?
}