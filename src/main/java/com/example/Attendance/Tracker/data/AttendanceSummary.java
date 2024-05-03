package com.example.Attendance.Tracker.data;

import java.time.Duration;
import java.util.Date;

public class AttendanceSummary {

    private final Date logIn;

    private final Date logOut;

    private final Duration workingTime;

    public AttendanceSummary(Date logIn, Date logOut, Duration workingTime) {
        this.logIn = logIn;
        this.logOut = logOut;
        this.workingTime = workingTime;
    }

    /**
     * Get the login time of the attendance
     *
     * @return the login time of the attendance
     */
    public Date getLogIn() {
        return logIn;
    }

    /**
     * Get the logout time of the attendance
     *
     * @return the logout time of the attendance
     */
    public Date getLogOut() {
        return logOut;
    }

    /**
     * Get the total working time in the attendance
     *
     * @return the total working time of the attendance
     */
    public Duration getWorkingTime() {
        return workingTime;
    }
}
