package Employee.data

import kotlinx.serialization.Serializable


@Serializable
data class EmployeeEntity(val employeeId: Int,
                          val employeeName:String,
                          val employeeEmail:String,
                          val employeeContact:Long,
                          val employeeSalary:Long,
                          val employeeJoiningDate:Long,
                          val employeeType: EmployeeType
)


enum class EmployeeType{
    Admin,Manager,Driver
}

object employee_table:org.jetbrains.exposed.sql.Table(){
    val employee_id= integer("employee_id")
    val employee_name=varchar("employee_name",60)
    val employee_email=varchar("employee_email",50)
    val employee_contact=long("employee_contact")
    val employee_salary=long("employee_salary")
    val employee_joining_date= long("employee_joining_date")
    val employee_type=enumerationByName<EmployeeType>("employee_type",10)
}