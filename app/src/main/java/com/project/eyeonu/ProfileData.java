package com.project.eyeonu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ProfileData extends SQLiteOpenHelper {


    static String DATABASE_NAME = "Track";
    static int DATABASE_VERSION = 5;
    static String TABLE_NAME = "Profile";
    static String UID = "UID";
    static String USERNAME = "Username";
    static String EMAIL = "Email";
    static String PHONE_N0 = "Phone_NO";
    static String COUNTRY = "Country";
    static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY " + "AUTOINCREMENT ," + USERNAME + " VARCHAR(60) ," + EMAIL +
            " VARCHAR(60), " + PHONE_N0 + " INTEGER , " + COUNTRY + " VARCHAR(100));";
    SQLiteDatabase db;
    Context context;
    String name, email, phn, cnt;

    public ProfileData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //Toast.makeText(context, "Constructor Called()", Toast.LENGTH_SHORT).show();
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        //Toast.makeText(context, "OnUpgrade"+oldVersion+ " "+newVersion, Toast.LENGTH_SHORT).show();
        //onCreate(db);

    }


    public long insertUser(String n, String em, String ph, String cntry) {
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, n);
        cv.put(EMAIL, em);
        cv.put(PHONE_N0, ph);
        cv.put(COUNTRY, cntry);
        long i = db.insert(TABLE_NAME, null, cv);
        return i;
    }

    public String getname() {
        String cols[] = {USERNAME, EMAIL, PHONE_N0, COUNTRY};
        Cursor cr = db.query(TABLE_NAME, cols, null, null, null, null, null);

        while (cr.moveToNext()) {
            name = cr.getString(0);
            email = cr.getString(1);
            phn = cr.getString(2);
            cnt = cr.getString(3);
        }
        return name;
    }

    public void clearProfile() {
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
