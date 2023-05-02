package common.dao

import vehicle.data.VehicleEntity
import workboard.data.WorkBoardEntity

interface WorkBoardDao {
    suspend fun addWorkboard(workboard:WorkBoardEntity):Int
    suspend fun getAllWorkBoard():List<WorkBoardEntity>?
    suspend fun getWorkboardById(workboardId:Int):List<WorkBoardEntity>?

    suspend fun delete(workboard: WorkBoardEntity)
}