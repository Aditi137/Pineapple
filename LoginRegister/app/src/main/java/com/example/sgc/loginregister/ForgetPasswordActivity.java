package com.example.sgc.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        etEmail = (EditText) findViewById(R.id.etEmail);

        final Button bReset = (Button) findViewById(R.id.bReset);
    }

    public void onClick(View v){
        String mail = etEmail.getText().toString();
        String type = "ResetPW";
        DatabaseWorker dbWorker = new DatabaseWorker(this);
        dbWorker.execute(type,mail);
    }
}
