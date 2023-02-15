package login.repository

import common.FleetSteerDatabase.dbQuery
import common.dao.LoginDao
import kotlinx.coroutines.runBlocking
import login.data.UserData
import login.data.Userdata
import login.usecase.data.UserType
import org.jetbrains.exposed.sql.*

class LoginDaoImpl : LoginDao {

    private fun resultRowtoUserData(row: ResultRow) = UserData(
        userID = row[Userdata.userID],
        username = row[Userdata.username],
        password = row[Userdata.password],
        usertype = row[Userdata.password]
    )

    override suspend fun getUid(username: String): UserData? = dbQuery {
        Userdata
            .select { Userdata.username eq username }
            .map(::resultRowtoUserData)
            .singleOrNull()
    }

    override suspend fun insertNewUser(userData: UserData): UserData? = dbQuery {
        val existingUser = Userdata.select { Userdata.userID eq userData.userID }
        if (existingUser != null) {
            throw IllegalArgumentException("user already exists")
        }
        val newUser = Userdata.insert {
            it[userID] = userData.userID
            it[username] = userData.username
            it[password] = userData.password
            it[usertype] = userData.usertype
        }
        newUser.resultedValues?.singleOrNull()?.let(::resultRowtoUserData)
    }

    override suspend fun getUserData(uId: Long): UserData? = dbQuery {
        Userdata
            .select { Userdata.userID eq uId }
            .map(::resultRowtoUserData)
            .singleOrNull()
    }

    override suspend fun getAllUsers(): List<UserData?> = dbQuery {
        Userdata.selectAll().map(::resultRowtoUserData)
    }

    override suspend fun generateId(): Long? = dbQuery {
        val uId = Userdata
            .insert { userID }
        uId.resultedValues?.singleOrNull()?.let(::resultRowtoUserData)?.userID
    }

    private suspend fun isFirstUser(): Boolean {
        return getAllUsers().isEmpty()
    }

    suspend fun insertAdmin(){
        if(isFirstUser()){
            insertNewUser(userData = UserData(userID = 1, username = "admin", password = "admin", usertype = UserType.Admin.name))
        }
    }



}

fun getLoginDaoInstance() = LoginDaoImpl().apply {
        runBlocking {try {
            insertAdmin()
        }catch (e:Exception){
            println("LoginCrash : $e")
        }

        }
    }
