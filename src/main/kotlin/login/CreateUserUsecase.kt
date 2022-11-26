package login

import data.model.AuthCredentials
import java.lang.IllegalArgumentException
import java.util.UUID

class CreateUserUsecase(private val repository: IAuthRepository): ICreateUserUsecase {

    override fun createUser(authCredentials: AuthCredentials, type: IAuthUsecase.UserType): Pair<Boolean, Error?> {
        val existingUID = repository.getUID(authCredentials.username)
        if(existingUID != null){
            return Pair(false, "Username Already Exist")
        }
        val uuID : Long = getNewUID()
        val userData = IAuthRepository.UserData(uID = uuID, username = authCredentials.username, password = authCredentials.password, type.name)
        try {
            repository.insertNewUser(userData)
        }catch (e: IllegalArgumentException){
            return Pair(false, e.message?: "something went wrong")
        }

        return Pair(true, null)
    }

    private fun getNewUID(): Long{

        return UUID.randomUUID().getMostSignificantBits();
    }
}