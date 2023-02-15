package login.usecase

import data.model.AuthCredentials
import login.usecase.data.UserType

typealias Error = String
interface ICreateUserUsecase {
    suspend fun createUser(authCredentials: AuthCredentials, type: UserType): Pair<Boolean, Error?>
}