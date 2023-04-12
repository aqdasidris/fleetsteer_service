package customer.usecase

import customer.data.CustomerEntity

interface ICustomerModificationUsecase {
    suspend fun addCustomer(customer:CustomerEntity):AddCustomerResult
    suspend fun deleteCustomer(customer: CustomerEntity)
}

sealed class AddCustomerResult{
    data class Success(val id:Int):AddCustomerResult()
    data class Failed(val error:String):AddCustomerResult()
}