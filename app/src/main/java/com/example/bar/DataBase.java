package com.example.bar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.jetbrains.annotations.NotNull;

public class DataBase extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db = getReadableDatabase();
    public DataBase(@NotNull Context context){
        super(context,"LessonLibrary.db",null,1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mylibrary ( "
                + " id integer PRIMARY KEY autoincrement, "
                + " booktitle text, "
                + " bookauthor text); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mylibrary");
        onCreate(db);
    }

    public void addBook(String title, String author){
        db.execSQL("insert into mylibrary (booktitle, bookauthor) values ('"
                + title+"', '"+author+"');" );
    }

    public Cursor getAllBook() {
        return db.rawQuery("select * from mylibrary", null);
    }

    public void deleteAllBooks(String id){
        db.execSQL("delete from mylibrary where id = '"+id+"';");
    }

    public void updateBook(String id){
        db.execSQL("update mylibrary set booktitle = 'aktualizacja' where id = '"+id+"';");
    }
}
