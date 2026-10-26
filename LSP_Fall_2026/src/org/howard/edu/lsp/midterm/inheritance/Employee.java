package org.howard.edu.lsp.midterm.inheritance;

/**
 * Abstract base class representing an employee in the payroll system.
 * Encapsulates shared employee attributes (hours worked and hourly rate)
 * and defines the polymorphic contract for calculating pay.
 *
 * @author [Saleh]
 */
public abstract class Employee {
    private final double hoursWorked;
    private final double hourlyRate;

    /**
     * Constructs an Employee with the given hours worked and hourly rate.
     *
     * @param hoursWorked the number of hours worked (nonnegative)
     * @param hourlyRate  the hourly pay rate (nonnegative)
     */
    public Employee(double hoursWorked, double hourlyRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    /**
     * Returns the number of hours worked by the employee.
     *
     * @return the hours worked
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Returns the hourly pay rate of the employee.
     *
     * @return the hourly rate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Calculates and returns the total pay for this employee.
     * Subclasses override this method to provide specific pay calculation rules.
     *
     * @return the calculated pay amount
     */
    public abstract double calculatePay();
}
