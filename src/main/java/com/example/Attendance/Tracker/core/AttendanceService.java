package com.example.Attendance.Tracker.core;

import com.example.Attendance.Tracker.data.GetMonthWIseSummaryResponse;
import com.example.Attendance.Tracker.data.GetMonthWiseSummaryRequest;
import com.example.Attendance.Tracker.data.MonthSummary;
import com.example.Attendance.Tracker.percistence.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    public GetMonthWIseSummaryResponse getSummary(final GetMonthWiseSummaryRequest request){

        Date originalDate = new Date();

        LocalDate localDate = originalDate.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();

        LocalDate currentMonth = localDate.withDayOfMonth(1);

        GetMonthWIseSummaryResponse response=new GetMonthWIseSummaryResponse();

        for(int i=0;i<request.getNumberOfMonths();i++){
            response.addMonthSummary(
                    new MonthSummary(
                            Date.from(currentMonth.atStartOfDay(ZoneId.of("UTC")).toInstant()),
                            attendanceRepository.getMonthSummary(currentMonth.getYear()*10000 +
                                    currentMonth.getMonthValue()*100 + currentMonth.getDayOfMonth())));

            currentMonth = currentMonth.minusMonths(1);
        }
        return response;
    }
}
