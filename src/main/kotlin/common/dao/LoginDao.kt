package common.dao

import login.data.UserData
import login.repository.IAuthRepository

interface LoginDao {
    suspend fun getUid(username:String):Long?
    suspend fun insertNewUser(userData: UserData):Boolean
    suspend fun getUserData(uId:Long):UserData?
    suspend fun getAllUsers():List<UserData?>
}