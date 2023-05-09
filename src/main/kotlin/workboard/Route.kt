package workboard

import Employee.Repository.EmployeeDaoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import job.repository.JobDaoImpl
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import vehicle.Usecase.AddVehicleResult
import vehicle.data.VehicleEntity
import vehicle.repository.VehicleDaoImpl
import vehicle.vehicleAddVehicleUsecase
import vehicle.vehicleUsecase
import workboard.data.WorkBoardEntity
import workboard.repository.WorkboardDaoImpl
import workboard.repository.WorkboardRepository
import workboard.usecase.AddWorkboardResult
import workboard.usecase.AddWorkboardUsecase
import workboard.usecase.WorkboardUsecase

val jobDao=JobDaoImpl().apply { runBlocking {  } }
val employeeDao=EmployeeDaoImpl().apply { runBlocking {  } }
val vehicleDao=VehicleDaoImpl().apply { runBlocking {  } }
val workBoardDao=WorkboardDaoImpl(vehicleDao, jobDao, employeeDao)
val repository=WorkboardRepository(workBoardDao)
val addWorkboardUsecase=AddWorkboardUsecase(repository)
val workboardUsecase=WorkboardUsecase(repository)

@Serializable
data class WorkboardNetworkModel( val vehicle:Int, val job:Int, val employee:Int, val status:Boolean )
fun Route.workBoardRoute(){
    post("/workboard") {
        val workboard=call.receive<WorkboardNetworkModel>()
        val result= addWorkboardUsecase.addWorkboard(workboard.vehicle, workboard.job, workboard.employee, workboard.status)
        when(result){
            is AddWorkboardResult.Failed -> call.response.status(HttpStatusCode.BadRequest)
            is AddWorkboardResult.Success -> {
                val id=result.workboardID
                val insertedWorkboard=workboard.copy()
                call.respond(status = HttpStatusCode.Created,insertedWorkboard)
            }
        }

    }


    get("/workboard"){
        val workboardData= workboardUsecase.getAllWorkboard()!!
        call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
        call.respond(workboardData)
    }

//    get("/vehicle/{vehicleId}"){
//        val id=call.parameters["vehicleId"]?.toInt() ?: -1
//        if (id>=0){
//                val vehicle= vehicleUsecase.getVehicleById(id)!!
//                call.respond(HttpStatusCode.Accepted,vehicle)
//        }
//        else call.respond(HttpStatusCode.BadRequest)
//
//    }

    delete("/workboard"){
        val id= call.parameters["id"]?.toInt() ?: -1
        if (id>=0) {
            val workboard = workboardUsecase.getWorkboardById(id = id)
            workboard?.let {
                workboardUsecase.deleteWorkboard(workboard)
                call.respond(HttpStatusCode.OK, workboard)
            } ?: call.respond(HttpStatusCode.OK)
        } else{
            call.respond(HttpStatusCode.BadRequest)
        }
    }

}