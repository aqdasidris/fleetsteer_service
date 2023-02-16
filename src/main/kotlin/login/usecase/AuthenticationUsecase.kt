package login.usecase

import login.data.UserData
import login.repository.IAuthRepository
import login.usecase.data.AuthUserData
import login.usecase.data.UserType
import kotlin.IllegalArgumentException

class AuthenticationUsecase(private val repository: IAuthRepository): IAuthUsecase {

    override suspend fun isUserValid(username: String, password: String): Pair<Boolean, Long?> {
        repository.getUID(username)?.let { uID ->
            repository.getUserData(uID)?.let { userData ->
                return Pair(userData.password==password,uID)
            }
        }
        return Pair(false, null)
    }

    override suspend fun getUserData(uID: Long): AuthUserData {
        return repository.getUserData(uID)?.toDomain()?: throw IllegalArgumentException("user not exist")
    }

//    override fun getUserData(uID: IAuthRepository.UserData): IAuthRepository.UserData {
//        return repository.getUserData(uID)?.toDomain()?: throw IllegalArgumentException("User Not Exist")
//    }

    private fun UserData.toDomain(): AuthUserData {
        return AuthUserData(
            uID = this.userID,
            username = this.username,
            type = UserType.valueOf(this.usertype)
        )
    }
}