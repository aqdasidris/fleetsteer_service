package login.repository

import common.FleetSteerDatabase.dbQuery
import common.dao.LoginDao
import data.model.Membersdata.autoIncrement
import kotlinx.coroutines.runBlocking
import login.data.UserData

import login.usecase.IAuthUsecase
import login.usecase.data.UserType
import org.jetbrains.exposed.sql.autoIncColumnType
import org.jetbrains.exposed.sql.isAutoInc
import java.lang.IllegalArgumentException

class LoginAuthRepository(val loginDao: LoginDao) : IAuthRepository {

   //private val defaultAdmin = UserData(1, "admin", "admin", usertype = UserType.Admin.name)



    override suspend fun getUID(username: String): Long? {
        val size = loginDao.getAllUsers().size
       println("XMPP: $size")
        /* val userId=loginDao.getUid(username)*/
        return size.toLong()
    }

    override suspend fun getUserData(uID: Long): UserData? {
        return loginDao.getUserData(uID)
    }

    override suspend fun insertNewUser(userData: UserData) {
        val existingUser = getUserData(userData.userID)
        if(existingUser!=null){
            throw IllegalArgumentException("User with same UID or Username already exist")
        }
        loginDao.insertNewUser(userData)
        println("Inserted new User: ${userData.username} Id: ${userData.userID}")
    }

    override suspend fun newUserID():Long?{
        return loginDao.generateId()
    }

}