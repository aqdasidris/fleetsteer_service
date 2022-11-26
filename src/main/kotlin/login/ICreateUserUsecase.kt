package login

import data.model.AuthCredentials

typealias Error = String
interface ICreateUserUsecase {
    fun createUser(authCredentials: AuthCredentials, type: IAuthUsecase.UserType): Pair<Boolean, Error?>
}