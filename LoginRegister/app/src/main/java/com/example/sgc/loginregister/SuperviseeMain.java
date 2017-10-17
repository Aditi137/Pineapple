package com.example.sgc.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//should be named SupervisorMain
public class SuperviseeMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisee_main);

        Button PersonalReminders =(Button) findViewById(R.id.bPersonalReminders);
        Button SuperviseeList =(Button) findViewById(R.id.bSuperviseeList);

        Button addSupervisee = (Button) findViewById(R.id.bAddSupervisee);

        final String UserName = getIntent().getStringExtra("User ID");
        Toast.makeText(getApplicationContext(),UserName,Toast.LENGTH_LONG).show();


        PersonalReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                intent.putExtra("User ID",UserName);
                intent.putExtra("Set By",UserName);
                startActivity(intent);
            }
        });

        addSupervisee.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LinkAccounts.class);
                startActivity(intent);

            }
        });


        SuperviseeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuperviseeWatchListActivity.class);
                intent.putExtra("User ID", UserName);
                startActivity(intent);
            }
        }
        );

    }
}
