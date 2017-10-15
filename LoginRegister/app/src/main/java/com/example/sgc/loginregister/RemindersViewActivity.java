package com.example.sgc.loginregister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by YY on 11/10/2017.
 */

public class RemindersViewActivity extends AppCompatActivity {

    private static final int ADD_REMINDER = 5;
    private static final int EDIT_REMINDER = 6;

    private AlertDialog.Builder selOptions;
    private AlertDialog.Builder cfmDel;


    private String reminder_title;
    private String reminder_date;
    private String reminder_time;
    private String reminder_status;

    private TextView tv_title;
    private TextView tv_date;
    private TextView tv_time;
    private TextView tv_status;

    private ReminderAdapter adapter;

    private int temp_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_view);

        selOptions = new AlertDialog.Builder(this);
        selOptions.setCancelable(true);
        selOptions.setTitle("Select Options");
        selOptions.setItems(new CharSequence[]{"Delete", "Edit", "View"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // Delete reminder

                        cfmDel = new AlertDialog.Builder(RemindersViewActivity.this);
                        cfmDel.setCancelable(true);
                        cfmDel.setTitle("Confirm delete reminder?");
                        cfmDel.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("check", String.valueOf(temp_pos));
                                adapter.remove(adapter.getItem(temp_pos));
                                adapter.notifyDataSetChanged();
                                Toast.makeText(RemindersViewActivity.this, "Reminder deleted.", Toast.LENGTH_LONG).show();

                            }
                        });
                        cfmDel.setNegativeButton("No", null);
                        cfmDel.show();

                        break;

                    case 1:
                        // Edit reminder

                        adapter.getItem(temp_pos);
                        Intent intent = new Intent(getApplicationContext(), EditReminderActivity.class);
                        startActivityForResult(intent, EDIT_REMINDER);



                        break;

                    case 2:
                        // View reminder in full details
                        // TODO

                        // Currently no extra details - Already viewing in full

                        break;

                }
            }
        });

        ListView listView = (ListView) findViewById(R.id.reminders_list);
        adapter = new ReminderAdapter(this, generateData());
        listView.setAdapter(adapter);

        Log.d("did","this");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                temp_pos = position;
                selOptions.show();
            }
        });




    }

    public ArrayList<Reminder> generateData() {
        ArrayList<Reminder> reminders = new ArrayList<Reminder>();
        ReminderDBHandler ReDB = new ReminderDBHandler(getApplicationContext());
        //Run once then delete the Start to end part
        //Start
        ReDB.addCol("Status","TEXT");
        ReDB.addCol("UserId","TEXT");
        ReDB.addCol("SetBy","TEXT");
        //End
        String UserName = getIntent().getStringExtra("User ID");
        UserName=UserName.trim();
        Cursor cursor =ReDB.getDBEntryForUser(UserName);
        cursor.moveToFirst();
        if(cursor.getCount() !=0) {
            int i = 0;
            while (!cursor.isAfterLast()) {
                Reminder temp = new Reminder(cursor.getInt(0), getApplicationContext());
                reminders.add(temp);
                cursor.moveToNext();
            }
        }

        return reminders;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_REMINDER:
                if (resultCode == Activity.RESULT_OK) {

                    // Get data from name of string holding the respective information
                    String temp_reminder_title = data.getStringExtra("title");
                    String temp_reminder_date = data.getStringExtra("date");
                    String temp_reminder_time = data.getStringExtra("time");
                    String temp_reminder_status = "On";

                    reminder_title = temp_reminder_title;
                    reminder_date = temp_reminder_date;
                    reminder_time = temp_reminder_time;
                    reminder_status = temp_reminder_status;

                    Log.d("title:" ,reminder_title);
                    Log.d("date:", reminder_date);
                    Log.d("time:", reminder_time);
                    Log.d("status:", reminder_status);

                    adapter.add(new Reminder("Title: " + reminder_title, "Date: " + reminder_date, "Time: " + reminder_time, "Status: " + reminder_status));

                }

                break;

            case EDIT_REMINDER:
                if (resultCode == Activity.RESULT_OK) {
                    String temp_reminder_title = data.getStringExtra("title");
                    String temp_reminder_date = data.getStringExtra("date");
                    String temp_reminder_time = data.getStringExtra("time");
                    String temp_reminder_status = "On";

                    reminder_title = temp_reminder_title;
                    reminder_date = temp_reminder_date;
                    reminder_time = temp_reminder_time;
                    reminder_status = temp_reminder_status;

                    adapter.remove(adapter.getItem(temp_pos));
                    adapter.add(new Reminder("Title: " + reminder_title, "Date: " + reminder_date, "Time: " + reminder_time, "Status: " + reminder_status));

                    Toast.makeText(this, "Reminder updated", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(this, "Edit cancelled", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplication()).inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_add_reminder:
                Intent intent = new Intent(getApplicationContext(), CreateReminderActivity.class);
                String UserName = getIntent().getStringExtra("User ID");
                intent.putExtra("User ID",UserName);
                intent.putExtra("Set By",UserName);
                startActivityForResult(intent, ADD_REMINDER);
                return true;
        }
        return false;
    }

}