package edu.lclark.homework6.SQLite;

import android.content.ContentValues;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class User implements BaseColumns{
    private String mUserName;
    private int mPins, mID;

    public static final String TABLE_NAME="users";
    public static final String COL_USER="username";
    public static final String COL_PINS="number of pins";

    public static final String CREATE_TABLE="CREATE TABLE " + User.TABLE_NAME + " ( " +
            User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            User._COUNT+ " TEXT, " +
            ")";

    private ArrayList<User> mUsers;


    public User(String user,int id){
        mUserName=user;
    }

    public User(int id,String user){
        mID=id;
        mUserName=user;
    }


    public String getUser(){
        return mUserName;
    }

    public int getPins(){
        return mPins;
    }


    public ContentValues getUserValues(){
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_USER,mUserName);
        contentValues.put(COL_PINS,mPins);
        return contentValues;
    }
}
