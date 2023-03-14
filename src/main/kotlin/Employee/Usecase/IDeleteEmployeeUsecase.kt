package Employee.Usecase

import Employee.data.EmployeeEntity

interface IDeleteEmployeeUsecase {
    suspend fun deleteEmployee(employeeData:EmployeeEntity)
}