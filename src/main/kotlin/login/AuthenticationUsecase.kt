package login

import kotlin.IllegalArgumentException

class AuthenticationUsecase(private val repository: IAuthRepository): IAuthUsecase {

    override fun isUserValid(username: String, password: String): Pair<Boolean, Long?> {
        repository.getUID(username)?.let { uID ->
            repository.getUserData(uID)?.let { userData ->
                return Pair(userData.password == password, uID)
            }
        }
        return Pair(false, null)
    }

    override fun getUserData(uID: Long): IAuthUsecase.UserData {
        return repository.getUserData(uID)?.toDomain()?: throw IllegalArgumentException("User Not Exist")
    }

    private fun IAuthRepository.UserData.toDomain(): IAuthUsecase.UserData{
        return IAuthUsecase.UserData(uID = this.uID, username = this.username, type = IAuthUsecase.UserType.valueOf(this.usertype))
    }
}