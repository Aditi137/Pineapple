package com.example.sgc.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        final Button bSupervisor = (Button) findViewById(R.id.bSurpervisor);
        final Button bSupervisee = (Button) findViewById(R.id.bSupervisee);

        bSupervisee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supervisorIntent = new Intent(AccountTypeActivity.this, RegisterActivity.class);
                AccountTypeActivity.this.startActivity(supervisorIntent);
            }
        });

        //may have to set listener on the acc type. to send by account type.
        //may look to see if can have just one var to handle both supervisor and supervisee.
    }
}
