package Employee.Repository

import Employee.data.EmployeeEntity
import Employee.data.employee_table
import common.dao.EntityDao
import org.jetbrains.exposed.sql.ResultRow

class EntityDaoImpl :EntityDao {
    override suspend fun getAllEmployees(): List<EmployeeEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun getEmployeeById(id: Int): EmployeeEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun addEmployee(employeeData: EmployeeEntity): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEntity(employeeData: EmployeeEntity) {
        TODO("Not yet implemented")
    }

    private fun resultRowToEmployeeEntity(row: ResultRow):EmployeeEntity{
        return EmployeeEntity(
            employeeId = row[employee_table.employee_id],
            employeeName = row[employee_table.employee_name],
            employeeEmail = row[employee_table.employee_email],
            employeeContact = row[employee_table.employee_contact],
            employeeSalary = row[employee_table.employee_salary],
            employeeJoiningDate = row[employee_table.employee_joining_date],
            employeeType = row[employee_table.employee_type]
        )
    }
}