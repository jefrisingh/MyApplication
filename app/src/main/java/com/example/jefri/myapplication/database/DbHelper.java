package com.example.jefri.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JEFRI SINGH(ஜெப்ரி சிங்) on 3/20/2016.
 * Organization "The Tuna Group" - Kerala
 */
public class DbHelper extends SQLiteOpenHelper{
    private static String DbName = "SimpleDatabase";
    private static int DbVersion = 1;

    // Table Names
    private static final String TABLE_USER = "User";

    // Common column names
    private static final String USER_NAME = "UserName";
    private static final String PASSWORD = "Password";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + USER_NAME + " TEXT PRIMARY KEY," + PASSWORD
            + " TEXT)";

    public DbHelper(Context context) {
        super(context, DbName, null, DbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_USER);
        onCreate(db);
    }

    public Boolean InsertUser(String userName,String password){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_USER+" WHERE "+USER_NAME+" == '"+userName+"';";
        Cursor c = db.rawQuery(query,null);
        if (c !=  null && c.moveToFirst()) {
            c.close();
            return false;
        }else {
            ContentValues values = new ContentValues();
            values.put(USER_NAME, userName);
            values.put(PASSWORD, password);

            db.insert(TABLE_USER, null, values);
            return true;
        }
    }

    public Boolean loginUser(String userName,String password){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_USER+" WHERE "+USER_NAME+" == '"+userName+"' AND "+PASSWORD+" == '"+password+"';";
        Cursor c = db.rawQuery(query,null);
        if (c != null && c.moveToFirst()) {
            c.close();
            return true;
        }
        return false;
    }
}
