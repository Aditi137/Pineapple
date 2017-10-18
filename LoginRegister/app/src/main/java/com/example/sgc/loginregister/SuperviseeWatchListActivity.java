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
        SharedPreferences sharedPref = getSharedPreferences("userID", Context.MODE_PRIVATE);
        String my_username = sharedPref.getString("username",null);

        //SuperviseeList.setAdapter(new ArrayAdapter<String>(SuperviseeWatchListActivity.this, android.R.layout.simple_list_item_1, names));

        String url = "http://10.0.2.2:8888/pineapple/getusers.php?my_username="+my_username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("users");

                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject user = array.getJSONObject(i);

                                // Get the current student (json object) data
                                String user_id = user.getString("u_id");

                                Toast.makeText(getApplicationContext(),user_id,Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
        requestQueue.add(jsObjRequest);

// Access the RequestQueue through your singleton class.





        /*
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

        */
    }
}
