//package com.example.Attendance.Tracker.percistence;
//
//import com.example.Attendance.Tracker.domain.MyDateTable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Repository;
//
//import java.util.Calendar;
//
//@Repository
//public class DateTablePopulatar implements CommandLineRunner {
//
//    @Autowired
//    DateTableRepository dateTableRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        populateDateTable();
//    }
//
//    private void populateDateTable() {
//        for (int year = 1900; year <= 2101; year++) {
//            for (int month = 1; month <= 12; month++) {
//                int daysInMonth = getDaysInMonth(year, month);
//                for (int day = 1; day <= daysInMonth; day++) {
//                    MyDateTable dateTable = new MyDateTable((short) year, (short) month, (short) day);
//                    dateTableRepository.save(dateTable);
//                }
//            }
//        }
//
//        System.out.println("Date table populated successfully.");
//    }
//
//    // Method to get the number of days in a month for a given year and month
//    private int getDaysInMonth(int year, int month) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month - 1); // Month is zero-based
//        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//    }
//}
