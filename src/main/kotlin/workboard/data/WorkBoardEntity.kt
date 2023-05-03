package workboard.data

import Employee.data.EmployeeEntity
import Employee.data.employee_table
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
    val employee: EmployeeEntity,
    val status:Boolean
)

object workBoardTable:Table(){
    val id=integer("id").autoIncrement()
    val job=integer("job").references(JobEntities.job_id)
    val employee=integer("employee").references(employee_table.employee_id)
    val vehicle=reference("vehicle",vehicle_table.vehicle_id)
    val status=bool("status")

    override val primaryKey=PrimaryKey(id)
}