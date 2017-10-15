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
import android.widget.TimePicker;
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
        final EditText details=(EditText) findViewById(R.id.ReDetails);
        final EditText title=(EditText) findViewById(R.id.ReTitle);
        final TimePicker tp=(TimePicker) findViewById(R.id.timePicker);
        final ReminderDBHandler ReDB = new ReminderDBHandler(this);
        //ReDB.addCol("Status","TEXT");
        MyEditTextDatePicker temp = new MyEditTextDatePicker(this,R.id.date1);
        final String[] Status_type= {"A&R Set","R Set","Ack","Not Ack"};
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                //On click goes to the create reminder in the alarm system
                String UserName = getIntent().getStringExtra("User ID");//Gets message for user ID
                UserName=UserName.trim();
                String setBy = getIntent().getStringExtra("Set By");// Message for set by
                setBy=setBy.trim();

                int hour = tp.getHour();
                int min=tp.getMinute();
                AlarmManager alarmMgr0 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

                String temp_date = date1.getText().toString();
                temp_date=temp_date.trim();
                String[] separated2 = temp_date.split("/");
                int day = Integer.parseInt(separated2[0]);
                int month = Integer.parseInt(separated2[1]);
                int year = Integer.parseInt(separated2[2]);
                //Toast.makeText(getApplicationContext(),"Day = "+day+" Mon="+month+" Year="+year,Toast.LENGTH_LONG).show();

                String event_details= details.getText().toString();
                String event_title=title.getText().toString();
                String status;
                int Rid=ReDB.getLastID()+1;//to get current ID
                if(UserName.equals(setBy))//This is for personal reminders. May have to add an additional message for type
                {

                    status=Status_type[0];
                    Intent i = new Intent(CreateReminderActivity.this,Alarm.class);
                    i.putExtra("User ID",UserName);
                    i.putExtra("ReID",Rid);
                    PendingIntent pi =PendingIntent.getBroadcast(getApplicationContext(),Rid,i,0);//Add in the ID

                    Calendar timeOff9 = Calendar.getInstance();
                    timeOff9.set(Calendar.YEAR,year);
                    timeOff9.set(Calendar.MONTH,month-1);//Calendars months are numbered 0-11 therefore -1 to get correct month
                    timeOff9.set(Calendar.DAY_OF_MONTH,day);
                    timeOff9.set(Calendar.HOUR_OF_DAY, hour);
                    timeOff9.set(Calendar.MINUTE, min);
                    timeOff9.set(Calendar.SECOND, 0);
                    alarmMgr0.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(), pi);
                    Toast.makeText(getApplicationContext(),"Alarm Created",Toast.LENGTH_LONG).show();
                }
                else// for reminders set by supervisor
                {
                    status=Status_type[1];
                }



                String time = hour +":"+min;
                Boolean Test = ReDB.insertData(temp_date,time,event_title,event_details,status,UserName,setBy);
                if(Test== true)
                {
                    Toast.makeText(getApplicationContext(),"Reminder Created",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"ID"+Rid,Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                startActivity(intent);
            }
            });
    }
}
