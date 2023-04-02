package customer.data

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class CustomerEntity(
    val customerId:Int,
    val companyName:String,
    val registrationNumber:String,
    val representativeName:String,
    val contact:Long,
    val email:String,
    val address:String,
)

object customerTable:Table(){
    val customer_id=integer("customer_id")
    val company_name=varchar("company_name",50)
    val registration_number=varchar("registration_name",50)
    val representative_name=varchar("represetative_name",50)
    val contact=long("contact")
    val email=varchar("email",50)
    val address=varchar("address",50)
}
