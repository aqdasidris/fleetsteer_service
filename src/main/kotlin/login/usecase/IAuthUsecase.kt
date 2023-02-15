package login.usecase

import login.usecase.data.AuthUserData

interface IAuthUsecase {
    suspend fun isUserValid(username: String, password: String): Pair<Boolean, Long?>
    suspend fun getUserData(uID: Long): AuthUserData

}