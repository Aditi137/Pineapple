package com.example.sgc.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        Button reminder = (Button) findViewById(R.id.remind_button);
        final String UserName = getIntent().getStringExtra("User ID");
        Toast.makeText(getApplicationContext(),UserName,Toast.LENGTH_LONG).show();

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), RemindersViewActivity.class);
                intent.putExtra("User ID",UserName);
                startActivity(intent);
            }
        });
    }
}
