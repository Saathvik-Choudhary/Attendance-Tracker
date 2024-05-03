package com.example.Attendance.Tracker.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @Column(name = "id",updatable = false,nullable = false)
    private Long id;

    @Column(name = "login",updatable = false,nullable = false)
    private Date login;

    @Column(name = "logout",nullable = false)
    private Date logout;

    @Column(name = "monthid",nullable = false,updatable = false)
    private int dateId;

    @Column(name = "workingHours",nullable = false)
    private Duration workingHours;

    private Attendance(){
        super();
    }

    public Attendance(final Date login) {

        Date logout = new Date(login.getTime());
        logout.setHours(23);
        logout.setMinutes(59);
        logout.setSeconds(59);

        setLogin(login);
        setLogout(logout);
        setDateId(login);
        setWorkingHours();
    }

    /**
     * Get the id of the attendance
     *
     * @return the id of the attendance
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the login time of the attendance
     *
     * @return the login time of the attendance
     */
    public Date getLogin() {
        return login;
    }

    /**
     * Get the logout time of the attendance
     *
     * @return the logout time of the attendance
     */
    public Date getLogout() {
        return logout;
    }

    /**
     * Get the date id of the attendance
     *
     * @return the date id of the attendance
     */
    public int getDateId() {
        return dateId;
    }

    /**
     * Get the total number of working time in that particular login logout cycle
     *
     * @return  the total number of working time in that particular login logout cycle
     */
    public Duration getWorkingHours() {
        return workingHours;
    }

    public void setLogin(Date login) {
        this.login = login;
    }

    public void setLogout(Date logout) {
        this.logout = logout;
    }

    public void setDateId(final Date login) {

        LocalDate localDate = login.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int dateid=localDate.getYear()*10000 + localDate.getMonthValue()*100 + localDate.getDayOfMonth();

        this.dateId=dateid;
    }

    public void setWorkingHours() {
        Duration duration = Duration.between(login.toInstant(), logout.toInstant());
    }
}
