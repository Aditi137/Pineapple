package com.example.sgc.loginregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.app.PendingIntent.getActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final RadioButton supervisor = (RadioButton) findViewById(R.id.rbsupervisor);
        final RadioButton supervisee = (RadioButton) findViewById(R.id.rbsupervisee);


        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String username = etUserName.getText().toString();
                final String password = etPassword.getText().toString();
                String account_type="0";

                if (supervisor.isChecked()){
                    account_type="1";
                }
                if (supervisee.isChecked()){
                    account_type="0";
                }


                Response.Listener<String> listener = new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {

                            if (response.trim().equals("Success")){
                                builder.setMessage("User account created!");
                                builder.setPositiveButton("Take me back to the login page", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        RegisterActivity.this.startActivity(loginIntent);
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else{
                                builder.setMessage("User account creation failed..!");
                                builder.setPositiveButton("Retry",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }

                        } catch (Error e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest =  new RegisterRequest(name, email, username, password,account_type, listener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });

    }
}
