package co.edu.escuelaing.cvds.lab7.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Objects;

@Builder
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    @Column(name = "FIRTS_NAME")
    private String firtsName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "SALARY")
    private double salary;


    //Constructors
    public Employee() {
    }
    public Employee (Long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(Long employeeId, String firtsName, String lastName, String role, double salary) {
        this.employeeId = employeeId;
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
    }
    public Employee( String firtsName, String lastName, String role, double salary) {
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
    }


    // Getters
    public Long getEmployeeId() {
        return employeeId;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    // setters


    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    // equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Double.compare(salary, employee.salary) == 0 && Objects.equals(employeeId, employee.employeeId) && Objects.equals(firtsName, employee.firtsName) && Objects.equals(lastName, employee.lastName) && Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firtsName, lastName, role, salary);
    }

}