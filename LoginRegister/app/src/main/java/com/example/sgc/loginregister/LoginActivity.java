package com.example.sgc.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterhere);
        final TextView forgetPwdLink = (TextView) findViewById(R.id.tvForgetpwd);


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

        forgetPwdLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetPwdIntent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                LoginActivity.this.startActivity(forgetPwdIntent);
            }
        });
    }

    public void onClick(View v){
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String type = "Login";


        SharedPreferences sharedPref = getSharedPreferences("userID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("username", username);
        editor.apply();

        //Temp Disabled to bypass db
        DatabaseWorker dbWorker = new DatabaseWorker(this);
        dbWorker.execute(type,username,password);

        /*


        //Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
        //intent.putExtra("User ID",username);
        //LoginActivity.this.startActivity(intent);

        //0 for supervisor
        if(accounttype == "0"){
            Intent intent = new Intent(LoginActivity.this, SuperviseeMain.class);

        }
        else{
            Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
        }


        intent.putExtra("User ID",username);
        //intent.putExtra("Account Type", accounttype);
        LoginActivity.this.startActivity(intent);
        */
    }

}
