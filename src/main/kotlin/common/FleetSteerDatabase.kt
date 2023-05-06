package common

import Employee.data.employee_table
import customer.data.customerTable
import job.data.JobEntities
import kotlinx.coroutines.Dispatchers
import login.data.UserDataTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import vehicle.data.vehicle_table
import workboard.data.workBoardTable
import java.sql.Connection

object FleetSteerDatabase {
    fun init(){
        val driverClassName="org.sqlite.JDBC"
        val jdbcURL = "jdbc:sqlite:./build/fleetsteer_db.db"
        val database = Database.connect(jdbcURL, driverClassName)

        transaction (database){
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(UserDataTable, JobEntities,vehicle_table,employee_table,customerTable,workBoardTable)
        }
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }


}