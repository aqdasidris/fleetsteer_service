package vehicle.Usecase

import vehicle.data.VehicleEntity

interface IAddVehicleUsecase {
    suspend fun addVehicle(vehicleEntity: VehicleEntity):AddVehicleResult
}

sealed class AddVehicleResult{
    data class Success(val vehicleID:Int):AddVehicleResult()
    data class Failed(val error:String):AddVehicleResult()
}