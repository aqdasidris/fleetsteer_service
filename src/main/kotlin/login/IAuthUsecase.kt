package login

import kotlinx.serialization.Serializable

interface IAuthUsecase {
    fun isUserValid(username: String, password: String): Pair<Boolean, Long?>
    fun getUserData(uID: Long): UserData

    @Serializable
    data class UserData(val uID: Long, val username: String, val type: UserType)

    enum class UserType{
        Manager, Driver, Admin
    }
}