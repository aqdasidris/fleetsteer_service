package customer.repository

import common.dao.CustomerDao
import customer.data.CustomerEntity

class CustomerRepository(val dao:CustomerDao): ICustomerRepository {
    override suspend fun getAllCustomer(): List<CustomerEntity?> {
        return dao.getAllCustomer() ?: emptyList()
    }

    override suspend fun getCustomerById(id: Int): CustomerEntity? {
       return dao.getCustomerById(id).first()
    }

    override suspend fun addCustomer(customer: CustomerEntity): Int {
        return dao.insertCustomer(customer)
    }

    override suspend fun deleteCustomer(customer: CustomerEntity) {
        return dao.deleteCustomer(customer)
    }
}