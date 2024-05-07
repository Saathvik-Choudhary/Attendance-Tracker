package com.example.Attendance.Tracker.core;

import com.example.Attendance.Tracker.data.*;
import com.example.Attendance.Tracker.domain.Attendance;
import com.example.Attendance.Tracker.percistence.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public GetAllAttendanceResponse getAllAttendance(final GetAllAttendanceRequest request){

        int pageSize = Math.max(request.getPageSize(), 100);

        Pageable pageable = PageRequest.of(request.getPageNumber(),
                pageSize,
                Sort.by("login").descending() );

        List<AttendanceSummary> attendanceSummaries = new ArrayList<>();

        for(Attendance attendance : attendanceRepository.findAll(pageable)){
            attendanceSummaries.add(new AttendanceSummary(attendance.getLogin(),
                    attendance.getLogout(),
                    attendance.getWorkingTime()));
        }

        final long totalCount = attendanceRepository.count();
        Page<AttendanceSummary> page = new PageImpl<>(attendanceSummaries, pageable, totalCount);

        return new GetAllAttendanceResponse(page);
    }

    public LogInResponse HandleLogIn(final LogInRequest request){
        Attendance attendance=new Attendance(request.getLogIn());

        attendance= attendanceRepository.save(attendance);

        LogInResponse response=new LogInResponse(attendance.getLogin());

        return response;
    }

    public LogOutResponse HandleLogOut(final LogOutRequest request){
        Long id= attendanceRepository.getLastEntry();

        Attendance attendance=attendanceRepository.getReferenceById(id);

        attendance.setLogout(request.getLogOut());

        return new LogOutResponse();
    }

    public AttendanceCheckResponse checkLogInStatus(AttendanceCheckRequest request) {

        Date requestDate=request.getCheckDate();

        LocalDate localDate = requestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int dateId=localDate.getYear()*10000 + localDate.getMonthValue()*100 + localDate.getDayOfMonth();

        Optional<Integer> id = attendanceRepository.findByDateId(dateId);

        if(id.isEmpty()) {
            return new AttendanceCheckResponse(true);
        }

        return new AttendanceCheckResponse(false);
    }

    public AttendanceCheckResponse checkLogOutStatus(AttendanceCheckRequest request) {

        Date requestDate=request.getCheckDate();

        LocalDate localDate = requestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int dateId=localDate.getYear()*10000 + localDate.getMonthValue()*100 + localDate.getDayOfMonth();

        Optional<Integer> id = attendanceRepository.findByDateId(dateId);

        if(id.isEmpty()) {
            return new AttendanceCheckResponse(false);
        }

        Date logoutTime=attendanceRepository.getLogout(dateId);

        if(requestDate.before(logoutTime))
        {
            return new AttendanceCheckResponse(true);
        }


        return new AttendanceCheckResponse(false);
    }
}
