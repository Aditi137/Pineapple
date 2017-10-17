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
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);
        Button create= (Button) findViewById(R.id.remind_update);
        //String UserName = getIntent().getStringExtra("User ID");
        final int ReID = getIntent().getIntExtra("ReID",0);
        final Reminder Re = new Reminder(ReID,getApplicationContext());
        final EditText date1 =(EditText) findViewById(R.id.date1);
        final EditText title=(EditText) findViewById(R.id.ReTitle);
        final EditText details=(EditText) findViewById(R.id.ReDetails);
        final TimePicker tp=(TimePicker) findViewById(R.id.timePicker);
        date1.setText(Re.getDate());
        String tempTime = Re.getTime();
        String[] tempSep = tempTime.split(":");
        tp.setHour(Integer.parseInt(tempSep[0]));
        tp.setMinute(Integer.parseInt(tempSep[1]));
        details.setText(Re.getDetails());
        title.setText(Re.getTitle());
        MyEditTextDatePicker temp = new MyEditTextDatePicker(this,R.id.date1);
        final String[] Status_type= {"A&R Set","R Set","Ack","Not Ack"};
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                //On click goes to the update reminder in the alarm system
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

                if(UserName.equals(setBy))//This is for personal reminders. May have to add an additional message for type
                {

                    Re.setStatus(Status_type[0]);
                    Intent i = new Intent(EditReminderActivity.this,Alarm.class);
                    i.putExtra("User ID",UserName);
                    i.putExtra("ReID",ReID);
                    PendingIntent pi =PendingIntent.getBroadcast(getApplicationContext(),ReID,i,0);//Add in the ID

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
                    Re.setStatus(Status_type[1]);
                }

                String time = hour +":"+min;
                Re.setDate(temp_date);
                Re.setDetails(event_details);
                Re.setTime(time);
                Re.setTitle(event_title);

                Toast.makeText(getApplicationContext(),"Reminder Updated",Toast.LENGTH_LONG).show();
//set that timer as a RTC Wakeup to alarm manager object

                //Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                //startActivity(intent);


                String date;
                String title = event_details;

                time = String.valueOf(hour) + ":" + String.valueOf(min);//No need to use this
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
