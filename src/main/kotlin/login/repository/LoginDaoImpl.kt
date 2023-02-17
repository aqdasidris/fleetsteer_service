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

    override suspend fun insertNewUser(userData: UserData): Boolean = dbQuery {
        if (!checkUserExist(userData.userID)) {
            try {
                insert(userData)
                return@dbQuery true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        false
    }

    private fun checkUserExist(uID: Long): Boolean {
        val existingUser = getUserByID(uID)
        val usernameResult = existingUser.map { it[UserDataTable.userID].toString() }
        return usernameResult.isNotEmpty()
    }

    private fun getUserByID(uID: Long) = UserDataTable.select { UserDataTable.userID eq uID }

    private fun insert(userData: UserData) {
        val newUser = UserDataTable.insert {
           /* it[userID] = userData.userID*/
            it[username] = userData.username
            it[password] = userData.password
            it[usertype] = userData.usertype
        }
    }

    override suspend fun getUserData(uId: Long): UserData? = dbQuery {
        UserDataTable
            .select { UserDataTable.userID eq uId }
            .map {
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

    private suspend fun isDbEmpty(): Boolean {
        return getAllUsers().isEmpty()
    }

    private suspend fun insertAdmin() {
        if (isDbEmpty()) {
            insertNewUser(
                userData = UserData(
                    userID = 1,
                    username = "admin",
                    password = "admin",
                    usertype = UserType.Admin.name
                )
            )
        }
    }


}
