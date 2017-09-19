package com.example.sgc.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        final EditText etEmail = (EditText) findViewById(R.id.etEmail);

        final Button bReset = (Button) findViewById(R.id.bReset);
    }
}
