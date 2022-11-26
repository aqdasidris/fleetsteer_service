package login

import java.lang.IllegalArgumentException

class InMemoryAuthRepository : IAuthRepository{

    private val defaultAdmin = IAuthRepository.UserData(1L, "admin", "admin", usertype = IAuthUsecase.UserType.Admin.name)
    private val storage: MutableList<IAuthRepository.UserData> = mutableListOf(defaultAdmin)

    override fun getUID(username: String): Long? {
        val userData = storage.find { it.username == username }
        return userData?.uID
    }

    override fun getUserData(uID: Long): IAuthRepository.UserData? {
        return storage.find { it.uID == uID }
    }

    override fun insertNewUser(userData: IAuthRepository.UserData) {
        val existingUser = storage.find { it.uID == userData.uID || it.username == userData.username }
        if(existingUser!=null){
            throw IllegalArgumentException("User with same UID or Username already exist")
        }
        storage.add(userData)
        println("Inserted new User: ${userData.username} Id: ${userData.uID}")
    }
}