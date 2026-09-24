package org.howard.edu.lsp.assignment3;

import java.util.List;

public class ETLTransformer {
    public List<Employee> transformData(List<Employee> employees) {
        for (Employee emp : employees) {
            emp.setName(emp.getName().toUpperCase());
            double calculatedTax = emp.getSalary() * 0.15;
            emp.setTax(calculatedTax);
        }
        return employees;
    }
}