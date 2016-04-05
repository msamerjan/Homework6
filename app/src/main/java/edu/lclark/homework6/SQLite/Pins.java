package edu.lclark.homework6.SQLite;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class Pins implements BaseColumns{

    private int mID, mLatitude,mLongitude;
    private String mTitle,mDescription;
    private User mUser;

    public static final String TABLE_NAME="Pin Position";
    public static final String COL_TITLE="Title";
    public static final String COL_DESCRIPTION="Description";
    public static final String COL_LONG="longitude";
    public static final String COL_LAT="latitude";

    public static final String CREATE_TABLE="CREATE TABLE"+TABLE_NAME+"("+_ID+"TEXT PRIMARY KEY,"+
            COL_LONG+"INT,"+COL_LAT+"INT )";

    public Pins(int latitude,int longitude,int ID,String title, String description){
        mLatitude=latitude;
        mLongitude=longitude;
        mID=ID;
        mDescription=description;
        mTitle=title;
    }

    public Pins(String title, String description){
        mDescription=description;
        mTitle=title;
    }

    public int getLatitude(){
        return mLatitude;
    }

    public int getLongitude(){
        return mLongitude;
    }

    public String getDescription(){
        return mDescription;
    }
    public String getTitle(){
    return mTitle;
    }

    public User getUser(){
        return mUser;
    }


    public ContentValues getPinValues(){
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_LONG,mLongitude);
        contentValues.put(COL_LAT,mLatitude);
        contentValues.put(COL_DESCRIPTION,mDescription);
        contentValues.put(COL_TITLE,mTitle);
        contentValues.put(_ID,mID);
        return contentValues;
    }
}
