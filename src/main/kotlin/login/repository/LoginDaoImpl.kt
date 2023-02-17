package login.repository

import common.FleetSteerDatabase.dbQuery
import common.dao.LoginDao
import kotlinx.coroutines.runBlocking
import login.data.UserData
import login.data.UserDataTable
import login.usecase.data.UserType
import org.jetbrains.exposed.sql.*

class LoginDaoImpl : LoginDao {

    private fun resultRowtoUserData(row: ResultRow): UserData {
       println("MappingUserData $row")
        return UserData(
            userID = row[UserDataTable.userID],
            username = row[UserDataTable.username],
            password = row[UserDataTable.password],
            usertype = row[UserDataTable.password]
        )
    }
    override suspend fun getUid(username: String): Long? = dbQuery {
        UserDataTable
         .select { UserDataTable.username eq username }.map {
             it[UserDataTable.userID]
            }.firstOrNull()
    }

    override suspend fun insertNewUser(userData: UserData): UserData? = dbQuery {
        val existingUser = UserDataTable.select { UserDataTable.userID eq userData.userID }
        if (existingUser != null) {
            throw IllegalArgumentException("user already exists")
        }
        val newUser = UserDataTable.insert {
            it[userID] = userData.userID
            it[username] = userData.username
            it[password] = userData.password
            it[usertype] = userData.usertype
        }
        newUser.resultedValues?.singleOrNull()?.let(::resultRowtoUserData)
    }

    override suspend fun getUserData(uId: Long): UserData? = dbQuery {
        UserDataTable
            .select { UserDataTable.userID eq uId }
            .map{
                UserData(
                    userID = it[UserDataTable.userID],
                    username = it[UserDataTable.username],
                    password = it[UserDataTable.password],
                    usertype = it[UserDataTable.usertype]
                )
            }
            .singleOrNull()
    }

    override suspend fun getAllUsers(): List<UserData?> = dbQuery {
        UserDataTable.selectAll().map(::resultRowtoUserData)
    }

    override suspend fun generateId(): Long? = dbQuery {
        val uId = UserDataTable
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
