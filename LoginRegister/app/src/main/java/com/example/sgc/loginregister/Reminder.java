package com.example.sgc.loginregister;

import android.app.Application;
import android.content.Context;

/**
 * Created by jon92 on 14/10/2017.
 */

public class Reminder extends Application {
    String date,time,title,detail,status,userid,setby;
    int id;
    public static Context context;
    public Reminder(int x)
    {
        context= getApplicationContext();
        id=x;
        final ReminderDBHandler ReDB = new ReminderDBHandler(context);

    }
}
