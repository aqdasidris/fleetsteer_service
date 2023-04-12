package common.dao

import customer.data.CustomerEntity

interface CustomerDao {
    suspend fun getAllCustomer():List<CustomerEntity?>
    suspend fun getCustomerById(id:Int):List<CustomerEntity?>
    suspend fun insertCustomer(customer:CustomerEntity):Int
    suspend fun deleteCustomer(customer: CustomerEntity)
}