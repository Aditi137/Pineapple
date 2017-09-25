package com.example.sgc.loginregister;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jon92 on 24/9/2017.
 */

public class CreateReminderActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        Button create= (Button) findViewById(R.id.remind_create);
        final EditText date1 =(EditText) findViewById(R.id.date1);
        final EditText time1 =(EditText) findViewById(R.id.time1);
        final EditText details=(EditText) findViewById(R.id.ReDetails);
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                //On click goes to the create reminder in the alarm system
                String temp_time = time1.getText().toString();//Correct this later
                String[] separated = temp_time.split(":");
                int hour = Integer.parseInt(separated[0]);
                int min = Integer.parseInt(separated[1]);
                Toast.makeText(getApplicationContext(),"Hour = "+hour+" min="+min,Toast.LENGTH_LONG).show();

                AlarmManager alarmMgr0 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

                String temp_date = date1.getText().toString();//Correct this later
                String[] separated2 = temp_date.split("/");
                int day = Integer.parseInt(separated2[0]);
                int month = Integer.parseInt(separated2[1]);
                int year = Integer.parseInt(separated2[2]);
                Toast.makeText(getApplicationContext(),"Day = "+day+" Mon="+month+" Year="+year,Toast.LENGTH_LONG).show();

                String event_details= details.getText().toString();
                //Add DB statements to store data

                Intent i = new Intent(CreateReminderActivity.this,Alarm.class);
                PendingIntent pi =PendingIntent.getBroadcast(getApplicationContext(),0,i,0);//Add in the ID

                Calendar timeOff9 = Calendar.getInstance();
                timeOff9.set(Calendar.DAY_OF_MONTH,day);
                timeOff9.set(Calendar.MONTH,month);
                timeOff9.set(Calendar.YEAR,year);
                timeOff9.set(Calendar.HOUR_OF_DAY, hour);
                timeOff9.set(Calendar.MINUTE, min);
                timeOff9.set(Calendar.SECOND, 0);

                Toast.makeText(getApplicationContext(),"Reminder Created",Toast.LENGTH_LONG).show();
//set that timer as a RTC Wakeup to alarm manager object
                alarmMgr0.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(), pi);
                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                startActivity(intent);
            }
            });
    }
}
