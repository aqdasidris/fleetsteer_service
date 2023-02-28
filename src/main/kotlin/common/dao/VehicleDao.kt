package common.dao

import vehicle.data.VehicleEntity

interface VehicleDao {
    suspend fun addVehicle(vehicleData:VehicleEntity):Int
    suspend fun getAllVehicleData():List<VehicleEntity>?

    suspend fun getVehiclebyId(vehicleId:Int):List<VehicleEntity>?

    suspend fun delete(vehicleEntity: VehicleEntity)
}