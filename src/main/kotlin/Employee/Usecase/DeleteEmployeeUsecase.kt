package Employee.Usecase

import Employee.Repository.IEmployeeRepository
import Employee.data.EmployeeEntity

class DeleteEmployeeUsecase(val employeeRepository: IEmployeeRepository): IDeleteEmployeeUsecase {
    override suspend fun deleteEmployee(employeeData: EmployeeEntity) {
        return employeeRepository.deleteEmployee(employeeData)
    }
}