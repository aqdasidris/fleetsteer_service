package data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.security.Timestamp

@Serializable
data class Job(
    val driver_id:String,
    val car_id: String,
    val lat_start: String,
    val long_start: String,
    val lat_end:String,
    val long_end:String,
    @Contextual
    val estimate_time: Long

    )
