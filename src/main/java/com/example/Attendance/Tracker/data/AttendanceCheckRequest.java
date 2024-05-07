package com.example.Attendance.Tracker.data;

import java.util.Date;

public class AttendanceCheckRequest {

    private final Date checkDate;

    public AttendanceCheckRequest(final Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }
}