package vehicle.repository

import common.FleetSteerDatabase.dbQuery
import common.dao.VehicleDao
import job.data.JobEntities
import org.jetbrains.exposed.sql.insert
import vehicle.data.VehicleEntity
import vehicle.data.vehicle_table

class VehicleDaoImpl:VehicleDao {
    override suspend fun addVehicle(vehicleData: VehicleEntity) {
        TODO("Not yet implemented")
    }

    private suspend fun insertVehicle(vehicleData: VehicleEntity):Int{
        val result=dbQuery{
            vehicle_table.insert {
                it[vehicle_id]=vehicleData.vehicleId
                it[vehicle_name]=vehicleData.vehicleName
                it[vehicle_number]=vehicleData.vehicleNumber
                it[vehicle_type]=vehicleData.vehicleType
                it[vehicle_driver_id]=vehicleData.vehicleDriverId
                it[vehicle_availability]=vehicleData.vehicleAvailability
            }
        }
        println("ADDED RESPONSE${result.resultedValues?.get(0)}")
        val id=result.resultedValues?.get(0)?.get(vehicle_table.vehicle_id)
        return id ?: -1
    }
    override suspend fun getAllVehicleData(): List<VehicleEntity> {
        TODO("Not yet implemented")
    }
}