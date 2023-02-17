package login.usecase

import data.model.AuthCredentials
import login.data.UserData
import login.repository.IAuthRepository
import login.usecase.data.UserType
import java.lang.IllegalArgumentException

class CreateUserUsecase(private val repository: IAuthRepository): ICreateUserUsecase {

    override suspend fun createUser(authCredentials: AuthCredentials, type: UserType): Pair<Boolean, Error?> {

        val existingUID = repository.getUID(authCredentials.username)
        if (existingUID != null) {
            return Pair(false, "Username Already Exist")
        }

        val userData =
            UserData(userID = -1, username = authCredentials.username, password = authCredentials.password, type.name)
        try {
            repository.insertNewUser(userData)
        } catch (e: IllegalArgumentException) {
            return Pair(false, e.message ?: "something went wrong")
        }

        return Pair(true, null)
    }
}



