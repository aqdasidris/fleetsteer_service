package vehicle.repository

import common.dao.VehicleDao
import vehicle.data.VehicleEntity

class VehicleRepository(val vehicleDao: VehicleDao):IVehicleRepository {
    override suspend fun addVehicle(vehicleEntity: VehicleEntity): Int {
        return vehicleDao.addVehicle(vehicleEntity)
    }
}