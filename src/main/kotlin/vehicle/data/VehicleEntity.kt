package vehicle.data

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class VehicleEntity(val vehicleId:Int,val vehicleName:String,val vehicleNumber:String,val vehicleType:String,val vehicleDriverId:Int,val vehicleAvailability:Boolean)

object vehicle_table: Table(){
    val vehicle_id=integer("vehicle_id")
    val vehicle_name=varchar("vehicle_name",50)
    val vehicle_number=varchar("vehicle_number",50)
    val vehicle_type=varchar("vehicle_type",50)
    val vehicle_driver_id=integer("vehicle_driver_id")
    val vehicle_availability=bool("vehicle_availabilty")
}