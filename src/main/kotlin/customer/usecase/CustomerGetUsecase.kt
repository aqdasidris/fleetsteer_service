package customer.usecase

import customer.data.CustomerEntity
import customer.repository.ICustomerRepository

class CustomerGetUsecase(val repo:ICustomerRepository):ICustomerGetUsecase {
    override suspend fun getAllCustomer(): List<CustomerEntity?> {
        return repo.getAllCustomer() ?: emptyList()
    }

    override suspend fun getCustomerById(id: Int): CustomerEntity? {
        return repo.getCustomerById(id)
    }
}