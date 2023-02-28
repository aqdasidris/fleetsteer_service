package vehicle.Usecase

import vehicle.data.VehicleEntity
import vehicle.repository.VehicleRepository
import java.lang.IllegalArgumentException

class AddVehicleUsecase(val vehicleRepository: VehicleRepository):IAddVehicleUsecase {
    override suspend fun addVehicle(vehicleEntity: VehicleEntity): AddVehicleResult {
        var result:AddVehicleResult=AddVehicleResult.Failed("")
        val newVehicleData=VehicleEntity(vehicleId = -1,
            vehicleName = vehicleEntity.vehicleName,
            vehicleNumber = vehicleEntity.vehicleNumber,
            vehicleType = vehicleEntity.vehicleType,
            vehicleDriverId = vehicleEntity.vehicleDriverId,
            vehicleAvailability = vehicleEntity.vehicleAvailability)
        try {
            val vehicleId=vehicleRepository.addVehicle(newVehicleData)
            if(vehicleId>=0){
                result=AddVehicleResult.Success(vehicleId)
            }
        }catch (e:IllegalArgumentException){
            result=AddVehicleResult.Failed(e.message ?: e.toString())
        }
        return result
    }
}