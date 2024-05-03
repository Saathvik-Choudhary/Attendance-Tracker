package com.example.Attendance.Tracker.data;

import com.example.Attendance.Tracker.common.PaginatedResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

public class GetAllAttendanceResponse extends PaginatedResponse {

    private final Page<AttendanceSummary> page;

    public GetAllAttendanceResponse(final Page<AttendanceSummary> page) {
        this.page = page;
    }


    /**
     * Get the page containing attendance month wise summary
     *
     * @return the page containing attendance month wise summary
     */
    @JsonProperty
    public Page<AttendanceSummary> getPage() {
        return page;
    }
}
