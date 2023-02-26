package common.dao

import vehicle.data.VehicleEntity

interface VehicleDao {
    suspend fun addVehicle(vehicleData:VehicleEntity)
    suspend fun getAllVehicleData():List<VehicleEntity>
}