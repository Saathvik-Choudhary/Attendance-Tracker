package com.example.Attendance.Tracker.data;

import com.example.Attendance.Tracker.common.Response;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GetMonthWIseSummaryResponse extends Response {

    private List<MonthSummary> monthSummaries;

    public GetMonthWIseSummaryResponse() {
        this.monthSummaries = new ArrayList<>();
    }

    public void addMonthSummary(MonthSummary summary){
        monthSummaries.add(summary);
    }

    /**
     * Get the list of month summaries
     *
     * @return the list of month summaries
     */
    @JsonProperty
    public List<MonthSummary> getMonthSummaries() {
        return monthSummaries;
    }
}
