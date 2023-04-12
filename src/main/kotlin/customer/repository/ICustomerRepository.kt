package customer.repository

import customer.data.CustomerEntity

interface ICustomerRepository {
    suspend fun getAllCustomer():List<CustomerEntity?>
    suspend fun getCustomerById(id:Int):CustomerEntity?
    suspend fun addCustomer(customer:CustomerEntity):Int
    suspend fun deleteCustomer(customer: CustomerEntity)
}