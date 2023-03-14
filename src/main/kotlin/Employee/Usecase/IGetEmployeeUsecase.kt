package Employee.Usecase

import Employee.data.EmployeeEntity

interface IGetEmployeeUsecase {
    suspend fun getAllEmployees():List<EmployeeEntity>?
    suspend fun getEmployeeById(employeeId:Int):EmployeeEntity?

}