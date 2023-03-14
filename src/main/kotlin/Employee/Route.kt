package Employee

import Employee.Repository.EmployeeDaoImpl
import Employee.Repository.EmployeeRepository
import Employee.Usecase.DeleteEmployeeUsecase
import Employee.Usecase.EmployeeAddResult
import Employee.Usecase.EmployeeAddUsecase
import Employee.Usecase.GetEmployeeUsecase
import Employee.data.EmployeeEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking

val employeeDao=EmployeeDaoImpl().apply { runBlocking {  } }
val employeeRepo=EmployeeRepository(employeeDao)
val addEmployeeUsecase=EmployeeAddUsecase(employeeRepo)
val getEmployeeUsecase=GetEmployeeUsecase(employeeRepo)
val deleteEmployeeUsecase=DeleteEmployeeUsecase(employeeRepo)

fun Route.employeeRoute(){
    post("/employee"){
        val employee=call.receive<EmployeeEntity>()
        val result= addEmployeeUsecase.addEmployee(employee)
        when(result){
            is EmployeeAddResult.FAILED -> call.response.status(HttpStatusCode.BadRequest)
            is EmployeeAddResult.SUCCESS -> {
                val id=result.employeeId
                val insertedEmployeeEntity=employee.copy(employeeId = id)
                call.respond(status = HttpStatusCode.Created,insertedEmployeeEntity)
            }
        }
    }

    get("/employee"){
        val employeeData= getEmployeeUsecase.getAllEmployees()!!
        call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
        call.respond(employeeData)
    }
}