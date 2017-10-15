package com.example.sgc.loginregister;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by YY on 15/10/2017.
 */

public class EditReminderActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);
        Button create= (Button) findViewById(R.id.remind_update);
        final EditText date1 =(EditText) findViewById(R.id.date1);

        final EditText details=(EditText) findViewById(R.id.ReDetails);
        final TimePicker tp=(TimePicker) findViewById(R.id.timePicker);
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                //On click goes to the update reminder in the alarm system
                int hour = tp.getHour();
                int min=tp.getMinute();
                AlarmManager alarmMgr0 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

                String temp_date = date1.getText().toString();
                String[] separated2 = temp_date.split("/");
                int day = Integer.parseInt(separated2[0]);
                int month = Integer.parseInt(separated2[1]);
                int year = Integer.parseInt(separated2[2]);
                //Toast.makeText(getApplicationContext(),"Day = "+day+" Mon="+month+" Year="+year,Toast.LENGTH_LONG).show();

                String event_details= details.getText().toString();
                //Add DB statements to store data

                Intent i = new Intent(EditReminderActivity.this,Alarm.class);
                PendingIntent pi =PendingIntent.getBroadcast(getApplicationContext(),0,i,0);//Add in the ID

                Calendar timeOff9 = Calendar.getInstance();
                timeOff9.set(Calendar.YEAR,year);
                timeOff9.set(Calendar.MONTH,month-1);//Calendars months are numbered 0-11 therefore -1 to get correct month
                timeOff9.set(Calendar.DAY_OF_MONTH,day);
                timeOff9.set(Calendar.HOUR_OF_DAY, hour);
                timeOff9.set(Calendar.MINUTE, min);
                timeOff9.set(Calendar.SECOND, 0);

                Toast.makeText(getApplicationContext(),"Day = "+timeOff9.get(Calendar.DAY_OF_MONTH)+" Mth="+(timeOff9.get(Calendar.MONTH)+1)+" Year="+timeOff9.get(Calendar.YEAR),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Reminder Updated",Toast.LENGTH_LONG).show();
//set that timer as a RTC Wakeup to alarm manager object
                alarmMgr0.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(), pi);
                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                //startActivity(intent);

                String time;
                String date;
                String title = event_details;

                time = String.valueOf(hour) + ":" + String.valueOf(min);
                date = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

                Intent data = new Intent();
                data.putExtra("title", title);
                data.putExtra("time", time);
                data.putExtra("date", date);

                setResult(RESULT_OK, data);

                finish();
            }
        });
    }




}
