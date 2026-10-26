package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents an independent contractor who receives a 15% premium on all hours worked.
 *
 * @author [Saleh]
 */
public class Contractor extends Employee {
    private static final double PREMIUM_MULTIPLIER = 1.15;

    /**
     * Constructs a Contractor with the specified hours worked and hourly rate.
     *
     * @param hoursWorked the number of hours worked (nonnegative)
     * @param hourlyRate  the base hourly rate (nonnegative)
     */
    public Contractor(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates total pay for a contractor, applying a 15% premium across all hours worked.
     *
     * @return the total calculated pay (hoursWorked * hourlyRate * 1.15)
     */
    @Override
    public double calculatePay() {
        return getHoursWorked() * getHourlyRate() * PREMIUM_MULTIPLIER;
    }
}
