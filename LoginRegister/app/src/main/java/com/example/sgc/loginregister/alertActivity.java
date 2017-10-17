package com.example.sgc.loginregister;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jon92 on 25/9/2017.
 */

public class alertActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertpage);
        final String UserName = getIntent().getStringExtra("User ID");
        final int Rid = getIntent().getIntExtra("ReID",0);

        final Reminder temp = new Reminder(Rid,getApplicationContext());
        final TextView text =(TextView) findViewById(R.id.textView);
        text.setText("Rid="+temp.getID()+"\n Date="+temp.getDate()+"\n Time="+temp.getTime()
            +"\nTitle= "+temp.getTitle()+"\n Details:"+temp.getDetails()+"\nUserID"+temp.getUserid());

        //Call Up data from DB here


        //final TextView text =(TextView) findViewById(R.id.textView);
        //text.setText("");

        Button snooze_bt = (Button) findViewById(R.id.snooze);
        Button ack =(Button) findViewById(R.id.ack);
        snooze_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Alarm.class);
                i.putExtra("User ID",UserName);
                i.putExtra("ReID",Rid);
                PendingIntent pi =PendingIntent.getBroadcast(getApplicationContext(),0,i,0);
                AlarmManager am =(AlarmManager) getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5*1000,pi);
                temp.setStatus("Not Ack");
                Intent intent = new Intent(getApplicationContext(), UserAreaActivity.class);//add destination here
                startActivity(intent);
            }
        });
        ack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UserAreaActivity.class);//add destination here
                temp.setStatus("Ack");
                intent.putExtra("User ID",UserName);
                startActivity(intent);

            }
        });
    }
}
