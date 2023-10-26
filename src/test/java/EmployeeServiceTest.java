import co.edu.escuelaing.cvds.lab7.controller.EmployeeController;
import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.repository.EmployeeRepository;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;
    private Employee employee;
    @BeforeEach
     void setup(){
        employee = Employee.builder()
                .employeeId(1L)
                .firtsName("Juan")
                .lastName("Daza")
                .role("Ingeniero")
                .salary(7500000)
                .build();
    }
    @Test
     void testConsultEmployeeById(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> actualEmployee = employeeService.getEmployeeById(1L);
        assertEquals(employee, actualEmployee.orElse(null));
    }
    @Test
     void testConsultNonExistentEmployee(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Employee> actualEmployee = employeeService.getEmployeeById(1L);
        assertFalse(actualEmployee.isPresent());
    }
    @Test
    void testDeleteEmployeeById(){
        long employeeId = 1L;
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
        assertTrue(true);
    }

    @Test

    public void givenEmployeeObject_whenDeleteEmployeeAndQueryEmployee_thenReturnNull(){
        // given - precondition or setup
        willDoNothing().given(employeeRepository).deleteById(employee.getEmployeeId());

        // when -  action or the behaviour that we are going test

        employeeService.deleteEmployee(employee.getEmployeeId());

        when(employeeService.getEmployeeById(employee.getEmployeeId())).thenReturn(null);

        // then - verify the output

        Optional<Employee> queryEmployee = employeeService.getEmployeeById(employee.getEmployeeId());

        assertThat(queryEmployee).isNull();

    }
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        // when -  action or the behaviour that we are going test
        when(employeeService.saveEmployee(employee)).thenReturn(employee);
        // then - verify the output
        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertThat(savedEmployee).isNotNull();
    }
}


