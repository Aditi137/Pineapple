package com.example.sgc.loginregister;


import android.content.Context;
import android.database.Cursor;

/**
 * Created by YY on 14/10/2017.
 */

public class Reminder{
    private int Rid;

    private String title;
    private String details,setby,userid;
    private String date;
    private String time;
    private String status;
    Context context1;


    public Reminder(String title, String date, String time, String status) {
        super();
        this.title = title;
        this.date = date;
        this.time = time;
        this.status = status;


    }
    public Reminder(int id,Context context)
    {
        super();

        Rid=id;
        context1=context;
        ReminderDBHandler ReDB =new ReminderDBHandler(context);
        Cursor temp = ReDB.getDBEntry(Rid);
        temp.moveToFirst();
        date=temp.getString(1);
        time=temp.getString(2);
        title=temp.getString(3);
        details=temp.getString(4);
        status=temp.getString(5);
        userid=temp.getString(6);
        setby=temp.getString(7);



    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        ReminderDBHandler ReDB =new ReminderDBHandler(context1);
        ReDB.updateEntry(Rid,"Title",title);
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        ReminderDBHandler ReDB =new ReminderDBHandler(context1);
        ReDB.updateEntry(Rid,"Date",date);
        this.date = date;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        ReminderDBHandler ReDB =new ReminderDBHandler(context1);
        ReDB.updateEntry(Rid,"Time",time);
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        ReminderDBHandler ReDB =new ReminderDBHandler(context1);
        ReDB.updateEntry(Rid,"Status",status);
        this.status = status;
    }
    public String getDetails() {
        return details;
    }

    public void setDetails(String details){
        ReminderDBHandler ReDB =new ReminderDBHandler(context1);
        ReDB.updateEntry(Rid,"Detail",status);
        this.details = details;
    }
    public String getUserid() {
        return userid;
    }
    public String getSetBy() {
        return setby;
    }
    public int getID() {
        return Rid;
    }

    public void deleteReminder()
    {
        ReminderDBHandler ReDB =new ReminderDBHandler(context1);
        ReDB.DeleteEntry(Rid);
    }



}
