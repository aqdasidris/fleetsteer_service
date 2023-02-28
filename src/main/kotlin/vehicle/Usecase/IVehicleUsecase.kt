package vehicle.Usecase

import vehicle.data.VehicleEntity

interface IVehicleUsecase {

    suspend fun getVehicleData():List<VehicleEntity>?

    suspend fun getVehicleById(vehicleId:Int):VehicleEntity?

    suspend fun deleteVehicle(vehicleEntity: VehicleEntity)

}