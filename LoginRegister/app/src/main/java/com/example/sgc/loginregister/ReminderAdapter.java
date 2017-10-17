package com.example.sgc.loginregister;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by YY on 14/10/2017.
 */

public class ReminderAdapter extends ArrayAdapter<Reminder> {
    private final Context context;
    private final ArrayList<Reminder> reminderArrayList;

    public ReminderAdapter(Context context, ArrayList<Reminder> reminderArrayList) {
        super(context, R.layout.list_reminders, reminderArrayList);
        this.context = context;
        this.reminderArrayList = reminderArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_reminders, parent, false);

        TextView tv_title = (TextView) rowView.findViewById(R.id.tv_title);
        TextView tv_date = (TextView) rowView.findViewById(R.id.tv_date);
        TextView tv_time = (TextView) rowView.findViewById(R.id.tv_time);
        TextView tv_status = (TextView) rowView.findViewById(R.id.tv_status);

        tv_title.setText(reminderArrayList.get(position).getTitle());
        tv_date.setText(reminderArrayList.get(position).getDate());
        tv_time.setText(reminderArrayList.get(position).getTime());
        tv_status.setText(reminderArrayList.get(position).getStatus());

        return rowView;


    }
}
