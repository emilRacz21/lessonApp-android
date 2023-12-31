package com.example.bar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

public class DatabaseActivity extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db = getReadableDatabase();

    public DatabaseActivity(@NotNull Context context) {
        super(context, "profileApp.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mylibrary ( "
                + " id integer PRIMARY KEY autoincrement, "
                + " profileLogin text, "
                + " profileName text, "
                + " profileForname text, "
                + " profilePhone text, "
                + " profileExtraInfo text, "
                + " profilePassword text, "
                + " profileRePassword text); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mylibrary");
        onCreate(db);
    }

    public void addBook(String profileLogin, String profileName, String profileForname, String profilePhone, String profileExtraInfo, String profilePassword, String profileRePassword) {
        db.execSQL("insert into mylibrary (profileLogin, profileName, profileForname, profilePhone, profileExtraInfo, profilePassword, profileRePassword) values ('"
                + profileLogin + "', '" + profileName + "', '" + profileForname + "', '" + profilePhone + "', '" + profileExtraInfo + "', '" + profilePassword + "', '" + profileRePassword + "');");
    }

    public Cursor getAllBook() {
        return db.rawQuery("select * from mylibrary", null);
    }

    public void deleteAllBooks(String id) {
        db.execSQL("delete from mylibrary where id = '" + id + "';");
    }

    public void updateDB(String id) {
        db.execSQL("update mylibrary set profileLogin = 'aktualizacja' where id = '" + id + "';");
    }

    public void addBookArray( EditText[] editTexts) {
        db.execSQL("insert into mylibrary (profileLogin, profileName, profileForname, profilePhone, profileExtraInfo, profilePassword, profileRePassword) values ('"
                + editTexts[0].getText().toString() + "', '" + editTexts[1].getText().toString() + "', '" + editTexts[2].getText().toString() + "', '" + editTexts[3].getText().toString() + "', '" + editTexts[4].getText().toString() + "', '" + editTexts[5].getText().toString() + "', '" + editTexts[6].getText().toString() + "');");
    }
}
