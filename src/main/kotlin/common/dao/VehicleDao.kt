package common.dao

import vehicle.data.VehicleEntity

interface VehicleDao {
    suspend fun addVehicle(vehicleData:VehicleEntity):Int
    suspend fun getAllVehicleData():List<VehicleEntity>
}