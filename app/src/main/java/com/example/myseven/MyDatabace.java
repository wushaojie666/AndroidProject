package com.example.myseven;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDatabace extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private Context mContext;

    public MyDatabace(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
         mContext = context;
    }


    public MyDatabace(Context context){

        super(context,"Contacts.db",null,1);

    }
    public static final String CREATE_Contacts="create table Contacts("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"sex text,"
            +"phone text)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Contacts);
        Log.v("Contacts","e");
        db.execSQL("insert into Contacts(id,name,sex,phone)values(?,?,?,?)",
                new String[]{"1","小明","男","886767421"});
        db.execSQL("insert into Contacts(id,name,sex,phone)values(?,?,?,?)",
                new String[]{"2","小h","女","886698421"});
        db.execSQL("insert into Contacts(id,name,sex,phone)values(?,?,?,?)",
                new String[]{"3","小l","男","886734421"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Contacts");
        onCreate(db);
    }
}



