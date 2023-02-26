package vehicle.repository.RemoteDataStore.data

import kotlinx.serialization.Serializable

@Serializable
data class VehicleNetworkDataModel(
    val vehicleId:Int,
    val vehicleName:String,
    val vehicleNumber:String,
    val vehicleType:String,
    val vehicleDriverId:Int,
    val vehicleAvailability:Boolean)
