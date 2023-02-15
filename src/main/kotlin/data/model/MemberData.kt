package data.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class MemberData(val id: Long ,val type:String)

object Membersdata:Table(){
    val id=long("id").autoIncrement()
    val type=varchar("type",50)
}
