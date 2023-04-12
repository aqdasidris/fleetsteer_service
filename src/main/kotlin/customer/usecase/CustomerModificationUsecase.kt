package customer.usecase

import Employee.Usecase.EmployeeAddResult
import customer.data.CustomerEntity
import customer.repository.ICustomerRepository

class CustomerModificationUsecase(val repository: ICustomerRepository):ICustomerModificationUsecase {
    override suspend fun addCustomer(customer: CustomerEntity): AddCustomerResult {
        var result:AddCustomerResult=AddCustomerResult.Failed("")
        val newCustomerEntity=CustomerEntity(
            customerId = customer.customerId,
            companyName =customer.companyName ,
            registrationNumber = customer.registrationNumber,
            representativeName =customer.representativeName ,
            contact= customer.contact,
            email = customer.email ,
            address = customer.address
        )
        try {
            val newCustomerId=repository.addCustomer(newCustomerEntity)
            if(newCustomerId>=0){
                result=AddCustomerResult.Success(newCustomerId)
            }
        }catch (e:IllegalArgumentException){
            result=AddCustomerResult.Failed(e.message ?: e.toString())
        }
        return result
    }

    override suspend fun deleteCustomer(customer: CustomerEntity) {
        repository.deleteCustomer(customer)
    }
}