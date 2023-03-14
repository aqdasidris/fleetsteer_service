package Employee.Usecase

import Employee.Repository.IEmployeeRepository
import Employee.data.EmployeeEntity

class GetEmployeeUsecase(val employeeRepository: IEmployeeRepository): IGetEmployeeUsecase {
    override suspend fun getAllEmployees(): List<EmployeeEntity>? {
        return employeeRepository.getAllEmployees()
    }

    override suspend fun getEmployeeById(employeeId: Int): EmployeeEntity? {
        return employeeRepository.getEmployeeById(employeeId)
    }
}