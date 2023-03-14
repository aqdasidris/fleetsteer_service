package Employee.Usecase

import Employee.Repository.EmployeeRepository
import Employee.Repository.IEmployeeRepository
import Employee.data.EmployeeEntity
import java.lang.IllegalArgumentException

class EmployeeAddUsecase(val employeeRepository: IEmployeeRepository) :IEmployeeAddUsecase {
    override suspend fun addEmployee(employeeData: EmployeeEntity): EmployeeAddResult {
       var result:EmployeeAddResult=EmployeeAddResult.FAILED("")
       val newEmployeeEntity=EmployeeEntity(
           employeeId = -1,
           employeeName = employeeData.employeeName,
           employeeEmail = employeeData.employeeEmail,
           employeeContact = employeeData.employeeContact,
           employeeSalary = employeeData.employeeSalary,
           employeeJoiningDate = employeeData.employeeJoiningDate,
           employeeType = employeeData.employeeType
       )
       try {
           val employeeId=employeeRepository.insertEmployee(newEmployeeEntity)
           if (employeeId>=0){
               result=EmployeeAddResult.SUCCESS(employeeId)
           }
       } catch (e:IllegalArgumentException){
           result=EmployeeAddResult.FAILED(e.message ?: e.toString())
       }
        return result
    }
}