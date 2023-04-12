package customer.repository

import Employee.data.employee_table
import common.FleetSteerDatabase.dbQuery
import common.dao.CustomerDao
import customer.data.CustomerEntity
import customer.data.customerTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CustomerDaoImpl:CustomerDao {

    private fun resultRowToCustomerEntity(row:ResultRow):CustomerEntity= CustomerEntity(
        customerId = row[customerTable.customer_id],
        companyName = row[customerTable.company_name],
        registrationNumber = row[customerTable.registration_number],
        representativeName = row[customerTable.representative_name],
        contact = row[customerTable.contact],
        email = row[customerTable.email],
        address = row[customerTable.address]
    )
    override suspend fun getAllCustomer(): List<CustomerEntity?> = dbQuery{
        customerTable.selectAll().map(::resultRowToCustomerEntity)
    }

    override suspend fun getCustomerById(id:Int): List<CustomerEntity?> {
        try{
            return getCustById(id)
        }catch (e:Exception){
            println("customer not found: $id")
        }
        return emptyList()
    }

    override suspend fun insertCustomer(customer: CustomerEntity): Int {
        var result=-1
        dbQuery {
            try {
                result=insCustomer(customer)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        return result
    }

    override suspend fun deleteCustomer(customer: CustomerEntity) {
        dbQuery { customerTable.deleteWhere { customerTable.customer_id eq customer.customerId } }
    }

    private suspend fun getCustById(id:Int):List<CustomerEntity?> = dbQuery{
        val customer=customerTable.select { customerTable.customer_id eq id }.map {
            CustomerEntity(
                customerId = it[customerTable.customer_id] ,
                companyName = it[customerTable.company_name],
                registrationNumber = it[customerTable.registration_number],
                representativeName = it[customerTable.representative_name],
                contact = it[customerTable.contact],
                email = it[customerTable.email],
                address =it[customerTable.address]
            )
        }
        return@dbQuery customer
    }

    private suspend fun insCustomer(customer: CustomerEntity):Int{
        val result=dbQuery{
            customerTable.insert {
               // it[customerTable.customer_id]= customer.customerId
                it[company_name]=customer.companyName
                it[registration_number]=customer.registrationNumber
                it[representative_name]=customer.representativeName
                it[contact]=customer.contact
                it[email]=customer.email
                it[address]=customer.address
            }
        }
        println("ADDED RESPONSE${result.resultedValues?.get(0)}")
        val id=result.resultedValues?.get(0)?.get(customerTable.customer_id)
        return id ?: -1
    }
}
