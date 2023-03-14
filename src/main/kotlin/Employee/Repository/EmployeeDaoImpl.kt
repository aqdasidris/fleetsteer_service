package Employee.Repository

import Employee.data.EmployeeEntity
import Employee.data.employee_table
import common.FleetSteerDatabase.dbQuery
import common.dao.EmployeeDao
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class EmployeeDaoImpl :EmployeeDao {
    override suspend fun getAllEmployees(): List<EmployeeEntity>? {
        return employee_table.selectAll().map(::resultRowToEmployeeEntity)
    }

    override suspend fun getEmployeeById(id: Int): List<EmployeeEntity>? {
       try {
           return selectEmployeeById(id)
       }catch (e:Exception){
           println("employee not found $e" )
       }
        return emptyList()
    }

    override suspend fun addEmployee(employeeData: EmployeeEntity): Int {
        var result=-1
        dbQuery {
            try {
                result=insertEmployee(employeeData)
            }catch (e:Exception){
               e.printStackTrace()
            }
        }
        return result
    }

    override suspend fun deleteEntity(employeeData: EmployeeEntity) {
        dbQuery { employee_table.deleteWhere { employee_table.employee_id eq employeeData.employeeId } }
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

    private suspend fun insertEmployee(employeeData: EmployeeEntity):Int{
        val result=dbQuery{
            employee_table.insert {
                it[employee_table.employee_id]=employeeData.employeeId
                it[employee_table.employee_name]=employeeData.employeeName
                it[employee_table.employee_email]=employeeData.employeeEmail
                it[employee_table.employee_contact]=employeeData.employeeContact
                it[employee_table.employee_salary]=employeeData.employeeSalary
                it[employee_table.employee_joining_date]=employeeData.employeeJoiningDate
                it[employee_table.employee_type]=employeeData.employeeType
            }
        }
        println("ADDED RESPONSE${result.resultedValues?.get(0)}")
        val id=result.resultedValues?.get(0)?.get(employee_table.employee_id)
        return id ?: -1
    }

    private suspend fun selectEmployeeById(employeeId:Int):List<EmployeeEntity>?=
        dbQuery {
            val employeeId=employee_table.select { employee_table.employee_id eq employeeId }.map {
                EmployeeEntity(
                    employeeId=it[employee_table.employee_id],
                    employeeName = it[employee_table.employee_name],
                    employeeContact = it[employee_table.employee_contact],
                    employeeEmail = it[employee_table.employee_email],
                    employeeSalary = it[employee_table.employee_salary],
                    employeeJoiningDate = it[employee_table.employee_joining_date],
                    employeeType = it[employee_table.employee_type]
                )
            }
            return@dbQuery employeeId
        }
}