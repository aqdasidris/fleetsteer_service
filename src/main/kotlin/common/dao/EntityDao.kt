package common.dao

import Employee.data.EmployeeEntity

interface EntityDao {
    suspend fun getAllEmployees():List<EmployeeEntity>?

    suspend fun getEmployeeById(id:Int):EmployeeEntity?

    suspend fun addEmployee(employeeData:EmployeeEntity):Int

    suspend fun deleteEntity(employeeData: EmployeeEntity)
}