package login.repository

import common.dao.LoginDao
import login.data.UserData

import java.lang.IllegalArgumentException

class LoginAuthRepository(val loginDao: LoginDao) : IAuthRepository {

   //private val defaultAdmin = UserData(1, "admin", "admin", usertype = UserType.Admin.name)


    override suspend fun getUID(username: String): Long? {
        return loginDao.getUid(username)
    }

    override suspend fun getUserData(uID: Long): UserData? {
        return loginDao.getUserData(uID)
    }

    override suspend fun insertNewUser(userData: UserData) {
        if(userData.userID < 0 ){
            val existingUser = getUserData(userData.userID)
            if(existingUser!=null){
                throw IllegalArgumentException("User with same UID or Username already exist")
            }
            loginDao.insertNewUser(userData)
        }
        println("Inserted new User: ${userData.username} Id: ${userData.userID}")
    }

}