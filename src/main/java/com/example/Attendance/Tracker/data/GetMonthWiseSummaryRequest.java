package com.example.Attendance.Tracker.data;

import com.example.Attendance.Tracker.common.Request;

public class GetMonthWiseSummaryRequest extends Request {

    private final int numberOfMonths;

    public GetMonthWiseSummaryRequest(final int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    /**
     * Get the number of months summary requested
     *
     * @return the number of months summary requested
     */
    public int getNumberOfMonths() {
        return numberOfMonths;
    }
}
