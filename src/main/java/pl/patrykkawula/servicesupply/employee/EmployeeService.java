package pl.patrykkawula.servicesupply.employee;

import org.springframework.stereotype.Service;
import pl.patrykkawula.servicesupply.employee.dtos.EmployeeCredentialsDto;
import pl.patrykkawula.servicesupply.employee.dtos.EmployeeSaveDto;
import pl.patrykkawula.servicesupply.employee.dtos.EmployeeViewDto;

import java.util.List;
import java.util.Optional;

@Service
class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDtoMapper employeeDtoMapper;

    EmployeeService(EmployeeRepository employeeRepository, EmployeeDtoMapper employeeDtoMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeDtoMapper = employeeDtoMapper;
    }

    Optional<EmployeeCredentialsDto> findCredentialsByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(employeeDtoMapper::mapToEmployeeCredentialsDto);
    }

    List<EmployeeViewDto> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeDtoMapper::mapToEmployeeViewDto)
                .toList();
    }


    void saveEmployee(EmployeeSaveDto employeeSaveDto) {
        employeeRepository.save(employeeDtoMapper.map(employeeSaveDto));
    }

    void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
