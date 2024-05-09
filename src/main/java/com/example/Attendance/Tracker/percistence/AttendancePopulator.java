package com.example.Attendance.Tracker.percistence;

import com.example.Attendance.Tracker.core.AttendanceService;
import com.example.Attendance.Tracker.domain.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Repository
public class AttendancePopulator implements CommandLineRunner {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public void run(String... args) throws Exception {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("MIT"));
        final var attendances = Arrays.asList(new Attendance(sdf.parse("2024-01-05 09:30:00")),
                new Attendance(sdf.parse("2024-01-03 09:30:00")),
                new Attendance(sdf.parse("2024-01-06 09:30:00")),
                new Attendance(sdf.parse("2024-01-10 10:30:00")),
                new Attendance(sdf.parse("2024-01-14 09:30:00")),
                new Attendance(sdf.parse("2024-01-16 10:30:00")),
                new Attendance(sdf.parse("2024-01-21 09:30:00")),
                new Attendance(sdf.parse("2024-01-24 10:30:00")),
                new Attendance(sdf.parse("2024-01-28 09:30:00")),
                new Attendance(sdf.parse("2024-01-31 10:30:00")),
                new Attendance(sdf.parse("2024-02-03 09:30:00")),
                new Attendance(sdf.parse("2024-02-06 09:30:00")),
                new Attendance(sdf.parse("2024-02-10 10:30:00")),
                new Attendance(sdf.parse("2024-02-13 09:30:00")),
                new Attendance(sdf.parse("2024-02-17 10:30:00")),
                new Attendance(sdf.parse("2024-02-20 09:30:00")),
                new Attendance(sdf.parse("2024-02-24 10:30:00")),
                new Attendance(sdf.parse("2024-02-27 09:30:00")),
                new Attendance(sdf.parse("2024-03-01 09:30:00")),
                new Attendance(sdf.parse("2024-03-05 09:30:00")),
                new Attendance(sdf.parse("2024-03-09 10:30:00")),
                new Attendance(sdf.parse("2024-03-12 09:30:00")),
                new Attendance(sdf.parse("2024-03-16 10:30:00")),
                new Attendance(sdf.parse("2024-03-19 09:30:00")),
                new Attendance(sdf.parse("2024-03-23 10:30:00")),
                new Attendance(sdf.parse("2024-03-26 09:30:00")),
                new Attendance(sdf.parse("2024-03-30 10:30:00")),
                new Attendance(sdf.parse("2024-04-02 09:30:00")),
                new Attendance(sdf.parse("2024-04-05 09:30:00")),
                new Attendance(sdf.parse("2024-04-09 10:30:00")),
                new Attendance(sdf.parse("2024-04-12 09:30:00")),
                new Attendance(sdf.parse("2024-04-16 10:30:00")),
                new Attendance(sdf.parse("2024-04-19 09:30:00")),
                new Attendance(sdf.parse("2024-04-23 10:30:00")),
                new Attendance(sdf.parse("2024-04-26 09:30:00")),
                new Attendance(sdf.parse("2024-04-30 10:30:00")));
//                new Attendance(sdf.parse("2024-05-03 09:30:00")),
//                new Attendance(sdf.parse("2024-05-07 09:30:00")),
//                new Attendance(sdf.parse("2024-05-10 10:30:00")),
//                new Attendance(sdf.parse("2024-05-14 09:30:00")),
//                new Attendance(sdf.parse("2024-05-17 10:30:00")),
//                new Attendance(sdf.parse("2024-05-21 09:30:00")),
//                new Attendance(sdf.parse("2024-05-24 10:30:00")),
//                new Attendance(sdf.parse("2024-05-28 09:30:00")),
//                new Attendance(sdf.parse("2024-05-31 10:30:00")));

        attendanceRepository.saveAll(attendances);
    }


    /*
    @Autowired
    private AttendanceRepository attendanceRepository;

    public static void main(String[] args) {
        SpringApplication.run(AttendancePopulator.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Calendar calendar = Calendar.getInstance();
        // Set the calendar to the current date
        calendar.setTime(new Date());

        // Populate data for the last 4 months
        for (int i = 0; i < 4; i++) {
            populateAttendanceForMonth(calendar);
            // Move calendar to the previous month
            calendar.add(Calendar.MONTH, -1);
        }
    }

    private void populateAttendanceForMonth(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate localDate = LocalDate.of(year, month, day);
            Date attendanceDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Attendance attendance = new Attendance(attendanceDate);
            attendanceRepository.save(attendance);
        }
    }

     */
}
