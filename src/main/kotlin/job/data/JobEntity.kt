package job.data

import kotlinx.serialization.Serializable
import login.IAuthRepository
import org.jetbrains.exposed.sql.Table


@Serializable
data class JobEntity(val job_id:Int, val name:String, val job_description:String,val delivery_address:String, val payment:Double, val contact:Int,val userId:Long)

object JobEntities: Table(){
    val job_id=integer("id").autoIncrement()
    val name=varchar("name",128)
    val job_description=varchar("job description",1048)
    val delivery_address=varchar("delivery_address",5000)
    val payment=double("payment")
    val contact=integer("contact")
    val userId=long("userId")

    override val primaryKey=PrimaryKey(job_id)
}