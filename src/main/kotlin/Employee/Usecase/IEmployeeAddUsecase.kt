package Employee.Usecase

import Employee.data.EmployeeEntity

interface IEmployeeAddUsecase {
    suspend fun addEmployee(employeeData:EmployeeEntity):EmployeeAddResult
}

sealed class EmployeeAddResult{
    data class SUCCESS(val employeeId:Int):EmployeeAddResult()

    data class FAILED(val error:String):EmployeeAddResult()
}