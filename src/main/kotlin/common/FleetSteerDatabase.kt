package common

import job.data.JobEntities
import login.IAuthRepository
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object FleetSteerDatabase {
    private var db:FleetSteerDatabase?=null
    fun init(){
        val driverClassName="org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction (database){
            SchemaUtils.create(JobEntities,IAuthRepository.Userdata)
        }
    }


}