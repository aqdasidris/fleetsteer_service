package vehicle.repository

import vehicle.data.VehicleEntity

interface IVehicleRepository {
    suspend fun addVehicle(vehicleEntity: VehicleEntity):Int

    suspend fun getAllVehicle():List<VehicleEntity>?

    suspend fun getVehicleById(vehicleId:Int):VehicleEntity?

    suspend fun deleteVehicle(vehicleEntity: VehicleEntity)
}