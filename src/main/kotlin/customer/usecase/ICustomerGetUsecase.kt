package customer.usecase

import customer.data.CustomerEntity

interface ICustomerGetUsecase {
    suspend fun getAllCustomer():List<CustomerEntity?>
    suspend fun getCustomerById(id:Int):CustomerEntity?
}