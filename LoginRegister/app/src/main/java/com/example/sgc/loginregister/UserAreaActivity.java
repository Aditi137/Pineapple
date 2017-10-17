package com.example.sgc.loginregister;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final String my_username;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Button reminder = (Button) findViewById(R.id.remind_button);
        SharedPreferences sharedPref = getSharedPreferences("userID", Context.MODE_PRIVATE);
        my_username = sharedPref.getString("username",null);


        final String UserName = getIntent().getStringExtra("User ID");
        //Toast.makeText(getApplicationContext(),UserName,Toast.LENGTH_LONG).show();


        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), RemindersViewActivity.class);
                intent.putExtra("User ID",UserName);
                intent.putExtra("Set By",UserName);
                startActivity(intent);
            }
        });

        Response.Listener<String> listener = new Response.Listener<String>() {

            public void onResponse(String response) {
                try {
                    if (response.trim().length()>0) {
                        final String supervisor_id = response.trim();
                        builder.setTitle("Request");
                        builder.setMessage("The user: " + response.trim() + " wants to link with your account");
                        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> listener = new Response.Listener<String>(){
                                    @Override
                                    public void onResponse(String response) {
                                        //
                                    }
                                };
                                CheckRequests accept = new CheckRequests(my_username, supervisor_id, 1, listener);
                                RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
                                queue.add(accept);
                                dialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> listener = new Response.Listener<String>(){
                                    @Override
                                    public void onResponse(String response) {
                                        //
                                    }
                                };
                                CheckRequests accept = new CheckRequests(my_username,supervisor_id,2, listener);
                                RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
                                queue.add(accept);
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                } catch (Error e) {
                    e.printStackTrace();
                }

            }
        };

        //TODO: Create a thread to check once every x second or so?


        CheckRequests checkRequests = new CheckRequests( my_username,"dummy_variable",0, listener);
        RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
        queue.add(checkRequests);



    }
}
