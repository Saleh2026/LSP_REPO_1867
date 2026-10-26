package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a full-time employee who earns regular hourly pay up to 40 hours per week
 * and overtime pay at 1.5 times their hourly rate for hours worked beyond 40.
 *
 * @author [Saleh]
 */
public class FullTimeEmployee extends Employee {
    private static final double REGULAR_HOURS_LIMIT = 40.0;
    private static final double OVERTIME_MULTIPLIER = 1.5;

    /**
     * Constructs a FullTimeEmployee with the specified hours worked and hourly rate.
     *
     * @param hoursWorked the number of hours worked per week (nonnegative)
     * @param hourlyRate  the base hourly rate (nonnegative)
     */
    public FullTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates weekly pay for a full-time employee, applying 1.5x overtime pay
     * for any hours worked beyond 40 hours.
     *
     * @return the total calculated pay
     */
    @Override
    public double calculatePay() {
        double hours = getHoursWorked();
        double rate = getHourlyRate();
        if (hours <= REGULAR_HOURS_LIMIT) {
            return hours * rate;
        }
        double overtimeHours = hours - REGULAR_HOURS_LIMIT;
        return (REGULAR_HOURS_LIMIT * rate) + (overtimeHours * rate * OVERTIME_MULTIPLIER);
    }
}
