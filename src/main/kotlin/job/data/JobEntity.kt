package job.data

import kotlinx.serialization.Serializable


@Serializable
data class JobEntity(val job_id:Int, val name:String, val job_description:String, val payment:Double, val contact:Int,val userId:Long)
