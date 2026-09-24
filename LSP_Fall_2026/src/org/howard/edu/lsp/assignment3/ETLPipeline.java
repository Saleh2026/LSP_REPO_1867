package org.howard.edu.lsp.assignment3;

import java.util.List;

public class ETLPipeline {
    public static void main(String[] args) {
        System.out.println("Starting Employee Payroll ETL Pipeline...");

        ETLExtractor extractor = new ETLExtractor();
        List<Employee> employees = extractor.extractData("input_employees.csv");

        ETLTransformer transformer = new ETLTransformer();
        List<Employee> transformedEmployees = transformer.transformData(employees);

        ETLLoader loader = new ETLLoader();
        loader.loadData(transformedEmployees, "output_payroll.csv");

        System.out.println("ETL Pipeline completed successfully.");
    }
}