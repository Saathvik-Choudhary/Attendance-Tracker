//package com.example.Attendance.Tracker.domain;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "date")
//public class MyDateTable {
//
//    @Id
//    @Column(name = "id", nullable = false, updatable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//
//    @Column(name = "date_id", nullable = false, updatable = false)
//    private int dateId;
//
//    @Column(name = "day", nullable = false, updatable = false)
//    private short day;
//
//    @Column(name = "month", nullable = false, updatable = false)
//    private short month;
//
//    @Column(name = "year", nullable = false, updatable = false)
//    private short year;
//
//    public MyDateTable() {
//        super();
//    }
//
//    public MyDateTable(final short year,
//                       final short month,
//                       final short day) {
//        this();
//
//        this.year = year;
//        this.month = month;
//        this.day = day;
//        setDateId();
//    }
//
//    private void setDateId() {
//        this.dateId =   year * 10000 +
//                        month * 100 +
//                        day;
//    }
//
//    /**
//     * Get the day of the date entity.
//     *
//     * @return the day of the date entity.
//     */
//    public short getDay() {
//        return day;
//    }
//
//    /**
//     * Get the month of the date entity.
//     *
//     * @return the month of the date entity.
//     */
//    public short getMonth() {
//        return month;
//    }
//
//    /**
//     * Get the year of the date entity.
//     *
//     * @return the year of the date entity.
//     */
//    public short getYear() {
//        return year;
//    }
//
//    /**
//     * Get the date if of the date entity.
//     *
//     * @return the date if of the date entity.
//     */
//    public int getDateId() {
//        return dateId;
//    }
//}
