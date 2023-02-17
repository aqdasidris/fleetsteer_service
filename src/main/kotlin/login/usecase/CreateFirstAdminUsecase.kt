package login.usecase

import data.model.AuthCredentials
import login.repository.IAuthRepository
import login.usecase.data.UserType

class CreateFirstAdminUsecase(private val createUserUsecase: CreateUserUsecase, private val authRepository: IAuthRepository) {
    suspend fun invoke(){

        val createUser = createUserUsecase.createUser(AuthCredentials("admin", "admin"), UserType.Admin)
        if(createUser.first){
                println("Admin Created")
        }else{
            println("Admin: ${createUser.second}")
        }

    }

}