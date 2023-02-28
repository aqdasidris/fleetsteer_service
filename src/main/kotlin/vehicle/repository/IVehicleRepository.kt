package vehicle.repository

import vehicle.data.VehicleEntity

interface IVehicleRepository {
    suspend fun addVehicle(vehicleEntity: VehicleEntity):Int
}