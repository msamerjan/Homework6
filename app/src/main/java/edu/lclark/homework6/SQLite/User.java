package edu.lclark.homework6.SQLite;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class User implements BaseColumns{
    private String mUserName;
    private int mPins;

    public static final String TABLE_NAME="users";
    public static final String COL_USER="username";
    public static final String COL_PINS="locations";

    public User(String user, int pins){
        mUserName=user;
        mPins=pins;
    }

    public String getUser(){
        return mUserName;
    }

    public int getPins(){
        return mPins;
    }

    public ContentValues getContentValues(){
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_USER,mUserName);
        contentValues.put(COL_PINS,mPins);
        return contentValues;
    }
}
