package customer.repository

import common.dao.CustomerDao
import customer.data.CustomerEntity

class CustomerDaoImpl:CustomerDao {
    override suspend fun getAllCustomer(): List<CustomerEntity?> {
        TODO("Not yet implemented")
    }

    override suspend fun getCustomerById(): List<CustomerEntity?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCustomer(customer: CustomerEntity): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCustomer(customer: CustomerEntity) {
        TODO("Not yet implemented")
    }
}