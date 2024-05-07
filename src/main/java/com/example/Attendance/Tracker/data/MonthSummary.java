package com.example.Attendance.Tracker.data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Storing the month and the total working hours in that month
 */
public class MonthSummary {

    private final Date month;

    private final BigDecimal totalWorkingHours;

    public MonthSummary(final Date month,
                        BigDecimal totalWorknigHours) {
        this.totalWorkingHours = totalWorknigHours;
        this.month = month;
    }

    /**
     * Get the month for which the total working hours are being stored
     *
     * @return the month for which the total working hours are being stored
     */
    public Date getMonth() {
        return month;
    }

    /**
     * Get the total number of working hours in that month
     *
     * @return the total number of working hours in that month
     */
    public BigDecimal getTotalWorkingHours() {
        return totalWorkingHours;
    }

}
