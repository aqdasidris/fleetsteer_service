package vehicle.repository

import common.dao.VehicleDao
import vehicle.data.VehicleEntity

class VehicleRepository(private val vehicleDao: VehicleDao):IVehicleRepository {
    override suspend fun addVehicle(vehicleEntity: VehicleEntity): Int {
        return vehicleDao.addVehicle(vehicleEntity)
    }

    override suspend fun getAllVehicle(): List<VehicleEntity>? {
        return vehicleDao.getAllVehicleData()
    }

    override suspend fun getVehicleById(vehicleId: Int): VehicleEntity? {
        return vehicleDao.getVehiclebyId(vehicleId)?.first()
    }

    override suspend fun deleteVehicle(vehicleEntity: VehicleEntity) {
         vehicleDao.delete(vehicleEntity)
    }
}