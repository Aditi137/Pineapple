package com.example.sgc.loginregister;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jon92 on 11/10/2017.
 */

public class ReminderDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Reminder.db";
    public static final String TABLE_NAME = "ReminderTable";
    public static final String Col1 = "RId";
    public static final String Col2 = "Date";
    public static final String Col3 = "Time";
    public static final String Col4 = "Title";
    public static final String Col5 = "Detail";
    public static final String Col6 = "Status";
    public static final String Col7 = "UserId";
    public static final String Col8 = "SetBy";

    public ReminderDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+ " (" + Col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Col2+" TEXT, "
                +Col3+" TEXT, "+Col4+" TEXT, "+Col5 +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String date,String time , String title, String detail, String status,String userid,String setby  )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalue = new ContentValues();
        contentvalue.put(Col2,date);
        contentvalue.put(Col3,time);
        contentvalue.put(Col4,title);
        contentvalue.put(Col5,detail);
        contentvalue.put(Col6,status);
        contentvalue.put(Col7,userid);
        contentvalue.put(Col8,setby);
        Long result = db.insert(TABLE_NAME,null,contentvalue);//method returns -1 if unsuccessful
        if(result==-1)
        {
            return false;
        }

        return true;
    }
    public void addCol (String name,String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE "+ TABLE_NAME+" ADD COLUMN "+name +" "+type);

    }
    public int getLastID()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT "+Col1+" FROM ReminderTable ORDER BY "+Col1+" DESC LIMIT 1;";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast();

        return cursor.getInt(0);
    }
    public Cursor getDBEntry(int id)//Get the entry based on Reminder ID
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM ReminderTable WHERE "+Col1+"='" +id+ "';";
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }
    public Cursor getDBEntryForUser(String id)//Use to get all reminders for that specifc ID
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM ReminderTable WHERE "+Col7+"='" +id+ "';";
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }
    public void updateEntry(int id,String col , String update)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE ReminderTable SET "+col+" = '"+update+"' WHERE "+Col1+" = '"+id+"';");

    }
    public void DeleteEntry(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM ReminderTable WHERE "+Col1+" = '"+id+"';");
    }
}
