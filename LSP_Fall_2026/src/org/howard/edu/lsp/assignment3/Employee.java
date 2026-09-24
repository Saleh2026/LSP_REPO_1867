package org.howard.edu.lsp.assignment3;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private double tax;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }
}