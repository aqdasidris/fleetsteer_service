package workboard.data

import Employee.data.EmployeeEntity
import Employee.data.employee_table
import customer.data.CustomerEntity
import customer.data.customerTable
import job.data.JobEntities
import job.data.JobEntity
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import vehicle.data.VehicleEntity
import vehicle.data.vehicle_table


@Serializable
data class WorkBoardEntity(
    val id:Int,
    val vehicle: VehicleEntity,
    val job: JobEntity,
    val price:JobEntity,
    val employee: EmployeeEntity,
    val status:Boolean
)

object workBoardTable:Table(){
    val id=integer("id").autoIncrement()
    val job=varchar("job",50).references(JobEntities.name)
    val price=double("price").references(JobEntities.payment)
    val employee=varchar("employee",50).references(employee_table.employee_name)
    val vehicle=varchar("vehicle",50).references(vehicle_table.vehicle_name)
    val status=bool("status")

    override val primaryKey=PrimaryKey(id)
}