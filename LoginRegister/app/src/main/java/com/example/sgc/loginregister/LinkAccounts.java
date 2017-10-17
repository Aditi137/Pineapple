package com.example.sgc.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

/**
 * Created by fjader on 2017-10-15.
 */

public class LinkAccounts extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_accounts);

        Button add_id = (Button) findViewById(R.id.add_button);
        final EditText user_id = (EditText) findViewById(R.id.user_id);

        add_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("userID", Context.MODE_PRIVATE);
                String my_username = sharedPref.getString("username",null);
                String supervisee_username = user_id.getText().toString();
                //Log.d("TAG",input+" "+my_username);

                Response.Listener<String> listener = new Response.Listener<String>(){
                    public void onResponse(String response){
                        try{
                            if (response.trim().equals("Success")){
                                Toast.makeText(getApplicationContext(),"Request sent",Toast.LENGTH_LONG).show();

                            }else if (response.trim().equals("User not found")){
                                Toast.makeText(getApplicationContext(),"There is no such user... Try again",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"You have already sent a request to that user",Toast.LENGTH_LONG).show();
                            }

                        }catch(Error e){
                            e.printStackTrace();
                        }
                    }

                };

                LinkRequest registerRequest =  new LinkRequest(my_username,supervisee_username,listener);
                RequestQueue queue = Volley.newRequestQueue(LinkAccounts.this);
                queue.add(registerRequest);

            }
        });
    }
}
