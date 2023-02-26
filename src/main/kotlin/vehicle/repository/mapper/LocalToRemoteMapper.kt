package vehicle.repository.mapper

import vehicle.data.VehicleEntity
import vehicle.repository.RemoteDataStore.data.VehicleNetworkDataModel

fun VehicleEntity.toNetworkModel():VehicleNetworkDataModel{
    return VehicleNetworkDataModel(
        vehicleId=this.vehicleId,
        vehicleName=this.vehicleName,
        vehicleNumber=this.vehicleNumber,
        vehicleType=this.vehicleType,
        vehicleDriverId=this.vehicleDriverId,
        vehicleAvailability = this.vehicleAvailability
    )
}