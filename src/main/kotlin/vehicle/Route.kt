package vehicle

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import job.addJobUsecase
import job.data.JobEntity
import job.usecase.AddJobResult
import kotlinx.coroutines.runBlocking
import vehicle.Usecase.AddVehicleResult
import vehicle.Usecase.AddVehicleUsecase
import vehicle.Usecase.VehicleUsecase
import vehicle.data.VehicleEntity
import vehicle.repository.VehicleDaoImpl
import vehicle.repository.VehicleRepository

val vehicleDao=VehicleDaoImpl().apply { runBlocking {  } }
val vehicleRepository=VehicleRepository(vehicleDao)
val vehicleAddVehicleUsecase=AddVehicleUsecase(vehicleRepository)
val vehicleUsecase=VehicleUsecase(vehicleRepository)
fun Route.vehicleRoute(){
    post("/vehicle") {
        val vehicle=call.receive<VehicleEntity>()
        val result= vehicleAddVehicleUsecase.addVehicle(vehicle)
        when(result){
            is AddVehicleResult.Failed -> call.response.status(HttpStatusCode.BadRequest)
            is AddVehicleResult.Success -> {
                val id=result.vehicleID
                val insertedVehicle=vehicle.copy(vehicleId = id)
                call.respond(status = HttpStatusCode.Created,insertedVehicle)
            }
        }

    }


    get("/vehicle"){
        val vehicleData= vehicleUsecase.getVehicleData()!!
        call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
        call.respond(vehicleData)
    }

    get("/vehicle/{vehicleId}"){
        val id=call.parameters["vehicleId"]?.toInt() ?: -1
        if (id>=0){
                val vehicle= vehicleUsecase.getVehicleById(id)!!
                call.respond(HttpStatusCode.Accepted,vehicle)
        }
        else call.respond(HttpStatusCode.BadRequest)

    }

    delete("/vehicle"){
        val id= call.parameters["vehicleId"]?.toInt() ?: -1
        if (id>=0) {
            val vehicle = vehicleUsecase.getVehicleById(vehicleId = id)
            vehicle?.let {
                vehicleUsecase.deleteVehicle(vehicle)
                call.respond(HttpStatusCode.OK, vehicle)
            } ?: call.respond(HttpStatusCode.OK)
        } else{
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
//
//is AddJobResult.Failed -> call.response.status(HttpStatusCode.BadRequest)
//is AddJobResult.Success -> {
//    val id=result.jobId
//    val insertedJob=job.copy(job_id = id)
//    call.respond(status = HttpStatusCode.Created,insertedJob)
//}