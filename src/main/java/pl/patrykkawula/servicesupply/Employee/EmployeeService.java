package pl.patrykkawula.servicesupply.Employee;

import org.springframework.stereotype.Service;
import pl.patrykkawula.servicesupply.Employee.dtos.EmployeeCredentialsDto;

import java.util.Optional;

@Service
class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCredentialsDtoMapper employeeCredentialsDtoMapper;

    EmployeeService(EmployeeRepository employeeRepository, EmployeeCredentialsDtoMapper employeeCredentialsDtoMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeCredentialsDtoMapper = employeeCredentialsDtoMapper;
    }

    Optional<EmployeeCredentialsDto> findCredentialsByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(employeeCredentialsDtoMapper::map);
    }
}
