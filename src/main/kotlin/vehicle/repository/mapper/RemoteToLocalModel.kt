package vehicle.repository.mapper

import vehicle.data.VehicleEntity
import vehicle.repository.RemoteDataStore.data.VehicleNetworkDataModel

fun VehicleNetworkDataModel.toDatabaseModel():VehicleEntity{
    return VehicleEntity(
        vehicleId = this.vehicleId,
        vehicleName = this.vehicleName,
        vehicleNumber = this.vehicleNumber,
        vehicleType = this.vehicleType,
        vehicleDriverId = this.vehicleDriverId,
        vehicleAvailability = this.vehicleAvailability
    )
}