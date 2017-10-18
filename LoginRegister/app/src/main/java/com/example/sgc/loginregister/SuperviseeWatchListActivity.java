package com.example.sgc.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
    String temp = "";
    String[] arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisee_watch_list);
        SuperviseeList = (ListView) findViewById(R.id.lvSuperviseeList);
        SharedPreferences sharedPref = getSharedPreferences("userID", Context.MODE_PRIVATE);
        String my_username = sharedPref.getString("username",null);

        String url = "http://10.0.2.2:8888/pineapple/getusers.php?my_username="+my_username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray array = response.getJSONArray("users");

                            for(int i=0;i<array.length();i++){
                                JSONObject user = array.getJSONObject(i);
                                temp += user.getString("u_id")+":";


                            }
                            arr = temp.split(":");
                            SuperviseeList.setAdapter(new ArrayAdapter<String>(SuperviseeWatchListActivity.this, android.R.layout.simple_list_item_1, arr));

                            SuperviseeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                                    startActivity(intent);

                                }
                            });

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
        requestQueue.add(jsObjRequest);


    }
}
