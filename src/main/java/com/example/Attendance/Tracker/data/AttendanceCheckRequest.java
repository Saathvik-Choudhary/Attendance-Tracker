package com.example.Attendance.Tracker.data;

import com.example.Attendance.Tracker.common.Request;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Date;

public class AttendanceCheckRequest extends Request {

    private final Date checkDate;

    @JsonCreator
    public AttendanceCheckRequest(final Date checkDate) {
        this.checkDate = checkDate;
    }


    /**
     * Get the check date and time stored in the request
     *
     * @return the check date and time stored in the request
     */
    public Date getCheckDate() {
        return checkDate;
    }
}