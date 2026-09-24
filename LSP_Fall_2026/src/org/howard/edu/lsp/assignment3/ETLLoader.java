package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ETLLoader {
    public void loadData(List<Employee> employees, String outputPath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            // Write CSV header
            bw.write("ID,Name,Department,Salary,Tax\n");
            
            for (Employee emp : employees) {
                String line = String.format("%d,%s,%s,%.2f,%.2f\n",
                        emp.getId(),
                        emp.getName(),
                        emp.getDepartment(),
                        emp.getSalary(),
                        emp.getTax());
                bw.write(line);
                
                // Print execution details to the console
                System.out.println("Processed & Loaded: " + emp.getName() + " ($" + emp.getSalary() + ")");
            }
            
            System.out.println("Payroll data successfully written to: " + outputPath);
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}