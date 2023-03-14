package Employee.Repository

import Employee.data.EmployeeEntity

interface IEmployeeRepository {
    suspend fun getAllEmployees():List<EmployeeEntity>?

    suspend fun getEmployeeById(employeeId:Int):EmployeeEntity?

    suspend fun insertEmployee(employeeData:EmployeeEntity):Int

    suspend fun deleteEmployee(employeeData:EmployeeEntity)
}