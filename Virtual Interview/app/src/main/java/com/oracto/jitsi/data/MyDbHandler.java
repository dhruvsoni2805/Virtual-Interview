package com.oracto.jitsi.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context){
        super(context, "login_db", null , 1);
    }
    @Override
    public void onCreate(SQLiteDatabase myDB){
            myDB.execSQL("create Table users(name Text,gender Text,number Text,username Text primary key,password Text)");
        }
    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion){
        myDB.execSQL("Drop Table if exists users");
    }

    public Boolean insertData(String name,String gender,String number, String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("number",number);
        contentValues.put("gender",gender);
        long result = myDB.insert("users",null,contentValues);
        if(result == -1) {
            return false;
        }
        else {
                return true;
        }
    }

    public Boolean checkusername(String username)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[] {username});
       if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkusernamepassword(String username , String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount() > 0){
            return  true;
        }
        else{
            return  false;
        }
    }

}
