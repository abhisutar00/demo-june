package com.demo.Controller;

import com.demo.Entity.Employee;
import com.demo.Payload.EmployeeDto;
import com.demo.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public String addEmployee(
        @RequestBody Employee employee
    ){
       employeeService.addEmployee(employee);
        return "Saved";
    }
    @DeleteMapping
    public String deleteEmployee(
            @RequestParam Long id
    ){
        employeeService.deleteEmployeeById(id);
        return "Deleted";
    }

    @PutMapping
    public String UpdateEmployee(
            @RequestParam Long id,
            @RequestBody EmployeeDto dto
    ){
        employeeService.updateEmployee(id, dto);
        return "Updated";
    }


    // http://localhost:8080/api/v1/employee?pageSize=3&pageNo=0&sortBy=email&sortDir=asc
    @GetMapping
    public List<Employee> getEmployees(
    @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
    @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
    @RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
    @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
    ){
        List<Employee> employees = employeeService.getEmployee(pageNo, pageSize, sortBy, sortDir);
        return employees;
    }
    
}
