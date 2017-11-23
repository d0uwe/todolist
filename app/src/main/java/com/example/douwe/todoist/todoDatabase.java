package com.example.douwe.todoist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by douwe on 11/20/17.
 */

public class todoDatabase extends SQLiteOpenHelper {
    static todoDatabase instance;
    private todoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, checked INTEGER)");

        System.out.println("holloh");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(db);
    }

    public static todoDatabase getInstance(Context context) {
        if (instance == null){
            instance = new todoDatabase(context, "todos", null, 1);
        }
        return instance;
    }

    public Cursor selectAll(){
        SQLiteDatabase kaas =  this.getWritableDatabase();
        Cursor l0l = kaas.rawQuery("SELECT rowid _id,* FROM todos", null);
        return l0l;

    }
    public void insert(String title, int completed){
        ContentValues values = new ContentValues() ;
        values.put("checked", completed);
        values.put("name", title);

        SQLiteDatabase kaas = this.getWritableDatabase();
        kaas.insert("todos", null, values);
    }

    public void delete(Integer id){
        SQLiteDatabase kaas = this.getWritableDatabase();
        kaas.delete("todos", "_id=?", new String[] { String.valueOf(id) });
    }

    public void update(Integer id, Integer new_status){
        SQLiteDatabase kaas = this.getWritableDatabase();
        ContentValues values = new ContentValues() ;
        values.put("checked", new_status);

        kaas.update("todos", values,  "_id=?", new String[] { String.valueOf(id) });
    }
}

