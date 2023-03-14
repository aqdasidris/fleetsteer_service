package Employee.Repository

import Employee.data.EmployeeEntity
import common.dao.EmployeeDao

class EmployeeRepository (val employeeDao:EmployeeDao): IEmployeeRepository {
    override suspend fun getAllEmployees(): List<EmployeeEntity>? {
        return employeeDao.getAllEmployees() ?: emptyList()
    }

    override suspend fun getEmployeeById(employeeId: Int): EmployeeEntity? {
        return employeeDao.getEmployeeById(employeeId)?.first()
    }

    override suspend fun insertEmployee(employeeData: EmployeeEntity):Int {
        return employeeDao.addEmployee(employeeData)
    }

    override suspend fun deleteEmployee(employeeData: EmployeeEntity) {
        employeeDao.deleteEntity(employeeData)
    }
}