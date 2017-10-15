package com.example.sgc.loginregister;

/**
 * Created by YY on 14/10/2017.
 */

public class Reminder {
    private String title;
    private String date;
    private String time;
    private String status;

    public Reminder(String title, String date, String time, String status) {
        super();
        this.title = title;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
