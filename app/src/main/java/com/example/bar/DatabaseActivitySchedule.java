package com.example.bar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.jetbrains.annotations.NotNull;

public class DatabaseActivitySchedule extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db = getReadableDatabase();

    public DatabaseActivitySchedule(@NotNull Context context) {
        super(context, "schedule.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table classSchedule ( "
                + " id integer PRIMARY KEY autoincrement, "
                + " timeBegin text, "
                + " timeEnd text, "
                + " schoolName text, "
                + " activityDate text); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS classSchedule");
        onCreate(db);
    }
    public void addSchedule(String timeBegin, String timeEnd, String schoolName, String activityDate) {
        db.execSQL("insert into classSchedule (timeBegin, timeEnd, schoolName, activityDate) values ('"
                + timeBegin + "', '" + timeEnd + "', '" + schoolName + "', '" + activityDate + "');");
    }
    public Cursor takeAllSchedules() {
        return db.rawQuery("select * from classSchedule", null);
    }
}
