package vehicle.Usecase

import vehicle.data.VehicleEntity
import vehicle.data.vehicle_table
import vehicle.repository.VehicleRepository

class VehicleUsecase(private val vehicleRepository: VehicleRepository):IVehicleUsecase {
    override suspend fun getVehicleData(): List<VehicleEntity>? {
        return vehicleRepository.getAllVehicle()
    }

    override suspend fun getVehicleById(vehicleId: Int): VehicleEntity? {
        return vehicleRepository.getVehicleById(vehicleId)
    }

    override suspend fun deleteVehicle(vehicleEntity: VehicleEntity) {
        vehicleRepository.deleteVehicle(vehicleEntity)
    }


}