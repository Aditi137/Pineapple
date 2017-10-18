package com.example.sgc.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jon92 on 24/9/2017.
 */
//Obsolete to be deleted
public class ReminderActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_main);
        Button createNew =(Button) findViewById(R.id.create_app);
        Button viewList =(Button) findViewById(R.id.app_list);
        final String UserName = getIntent().getStringExtra("User ID");


        // HOW TO GET USER ID
        SharedPreferences sharedPref = getSharedPreferences("userID", Context.MODE_PRIVATE);
        String my_username = sharedPref.getString("username",null);



        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //On click goes to the create reminder
                Intent intent = new Intent(getApplicationContext(), CreateReminderActivity.class);
                intent.putExtra("Set By",UserName);
                intent.putExtra("User ID",UserName);
                startActivity(intent);
            }
        }
        );
        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(getApplicationContext(), RemindersViewActivity.class);
                startActivity(intent);
            }
        }
        );
    }
}
