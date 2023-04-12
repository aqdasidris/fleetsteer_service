package customer

import customer.data.CustomerEntity
import customer.repository.CustomerDaoImpl
import customer.repository.CustomerRepository
import customer.usecase.AddCustomerResult
import customer.usecase.CustomerGetUsecase
import customer.usecase.CustomerModificationUsecase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking

val customerDao=CustomerDaoImpl().apply { runBlocking {  } }
val repository=CustomerRepository(customerDao)
val getCustomerUsecase=CustomerGetUsecase(repository)
val modifyCustomerUsecase=CustomerModificationUsecase(repository)

fun Route.customer(){
    get("/customer") {
        val customers= getCustomerUsecase.getAllCustomer()
        call.application.environment.log.info("query params: ${call.request.queryParameters.toMap()}")
        call.respond(customers)
    }
    get("/customer/{customer_id}"){

    }

    post("/customer"){
        val customer=call.receive<CustomerEntity>()
        val result= modifyCustomerUsecase.addCustomer(customer)
        when(result){
            is AddCustomerResult.Failed -> call.response.status(HttpStatusCode.BadRequest)
            is AddCustomerResult.Success -> {
                val id=result.id
                val insertedCustomer=customer.copy(id)
                call.respond(status = HttpStatusCode.Created,insertedCustomer)
            }
        }
    }

    delete("/customer") {
        val id=call.parameters["customerId"]?.toInt() ?:-1
        if(id>0){
            val customer= getCustomerUsecase.getCustomerById(id)
            customer?.let {
                modifyCustomerUsecase.deleteCustomer(customer)
                call.respond(HttpStatusCode.OK,customer)
            }?:call.respond(HttpStatusCode.OK)
        }else{
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}

