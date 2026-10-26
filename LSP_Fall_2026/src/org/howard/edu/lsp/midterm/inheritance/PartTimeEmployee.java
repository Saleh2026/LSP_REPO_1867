package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a part-time employee who receives straight hourly pay for all hours worked.
 *
 * @author [Saleh]
 */
public class PartTimeEmployee extends Employee {

    /**
     * Constructs a PartTimeEmployee with the specified hours worked and hourly rate.
     *
     * @param hoursWorked the number of hours worked (nonnegative)
     * @param hourlyRate  the hourly pay rate (nonnegative)
     */
    public PartTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates straight hourly pay for a part-time employee.
     *
     * @return the total calculated pay (hoursWorked * hourlyRate)
     */
    @Override
    public double calculatePay() {
        return getHoursWorked() * getHourlyRate();
    }
}
