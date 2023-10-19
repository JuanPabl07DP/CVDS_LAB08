package co.edu.escuelaing.cvds.lab7.controller;

import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("/consult-all")
    public  String consultAllEmployees(Model model){
        List<Employee> lista = employeeService.getEmployees();
        model.addAttribute("lista_objects",lista);
        return  "showAllEmployees";
    }
    @GetMapping("/consult-id")
    public String consultById(@RequestParam(name = "id", required = false, defaultValue = "1") long name, boolean modificate,  Model model ){
        Optional<Employee> e = employeeService.getEmployeeById((Long)name);
        if (e.isPresent()){
            Employee employee = e.get();
            model.addAttribute("employee", employee);
        }
        else{
            return "modificateFail";
        }
        String change = modificate ? "modificateEmployee":"showEmployee";
        return change;
    }
    @GetMapping("/create-new")
    public String createEmployee(Model model){
        return "createEmployee";
    }
    @PostMapping("/create-new")
    public String createEmployee(String firtsName, String lastName, String role, double salary){
        try{
        Employee e = new Employee(firtsName,lastName, role, salary);
        employeeService.saveEmployee(e);
        return "createdSuccessful";
        }catch (Exception e){
            return"createdFail";
        }
    }
    @PostMapping("/update-employee")
    public String updateEmployee(Long id,String firtsName, String lastName, String role, double salary){
        try{
        Employee e = new Employee(id,firtsName,lastName, role, salary);
        employeeService.saveEmployee(e);
        return "modificateSuccessful";
        }catch (Exception e){
            return "modificateFail";
        }
    }
    @PostMapping("/delete-employee")
    public String deleteEmployee(Long id){
        String template = employeeService.deleteEmployee(id) ? "deleteSuccessful":"deleteFail";
        return template;

    }
    @GetMapping("/employee")
    public String employee( Model model) {
        employeeService.createEmployee();
        return "greeting";
    }

}
