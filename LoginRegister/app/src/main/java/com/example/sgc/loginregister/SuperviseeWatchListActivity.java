package com.example.sgc.loginregister;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SuperviseeWatchListActivity extends AppCompatActivity {

    private ListView SuperviseeList;
    //String[] names;
    InputStream is = null;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisee_watch_list);

        final String UserName = getIntent().getStringExtra("User ID");
        SuperviseeList = (ListView) findViewById(R.id.lvSuperviseeList);

        //SuperviseeList.setAdapter(new ArrayAdapter<String>(SuperviseeWatchListActivity.this, android.R.layout.simple_list_item_1, names));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try{
            URL url = new URL("http://10.0.2.2:8888/pineapple/superviseelist.php");//http post
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

            urlconnection.setRequestMethod("POST");

            is = new BufferedInputStream(urlconnection.getInputStream());//not sure if need Buffered Input Stream


        }catch (IOException e) {
            e.printStackTrace();
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine())!=null){
                sb.append(line+"\n");
                result = sb.toString();
                is.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        try{
            JSONArray jArray  = new JSONArray(result);
            int count = jArray.length();

            for(int i = 0; i<count; i++){
                JSONObject json_data = jArray.getJSONObject(i);
                temp+= json_data.getString("u_id")+":";
            }

            arr = temp.split(":");
            SuperviseeList.setAdapter(new ArrayAdapter<String>(SuperviseeWatchListActivity.this, android.R.layout.simple_list_item_1, arr));

            SuperviseeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                    intent.putExtra("User ID",UserName);
                    startActivity(intent);

                }
            });

        }catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
