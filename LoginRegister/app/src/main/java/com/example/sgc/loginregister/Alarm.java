package com.example.sgc.loginregister;

/**
 * Created by jon92 on 25/9/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jon92 on 24/9/2017.
 */

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm...",Toast.LENGTH_LONG).show();
        String UserName = intent.getStringExtra("User ID");//Gets message for user ID
        int Rid = intent.getIntExtra("ReID",-1);
        Intent i = new Intent();
        i.putExtra("User ID",UserName);
        i.putExtra("ReID",Rid);
        i.setClassName("com.example.sgc.loginregister", "com.example.sgc.loginregister.alertActivity");//have to change this
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
