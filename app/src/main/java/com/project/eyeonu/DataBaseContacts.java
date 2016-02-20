package com.project.eyeonu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseContacts extends SQLiteOpenHelper {
    public static int dbver = 2;
    static String dbname = "DBContacts";
    static String tbname = "Contacts";
    static String Name = "Name";
    static String CNumber = "TelNumber";
    static String CREATE_TABLE = "CREATE TABLE " + tbname + " ( " + Name + " VARCHAR(20)," + CNumber + " VARCHAR(20) );";
    Context context;
    SQLiteDatabase db;
    String[] names = new String[5];
    String[] numbers = new String[5];

    public DataBaseContacts(Context context) {
        super(context, dbname, null, dbver);
        this.context = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {
        db.execSQL("DROP TABLE IF EXISTS " + tbname);
        Toast.makeText(context, "DataBase Version Upgraded", Toast.LENGTH_SHORT).show();
        onCreate(db);
    }

    public long insertContacts(String name, String number1) {
        ContentValues cv = new ContentValues();
        cv.put(Name, name);
        cv.put(CNumber, number1);
        long i = db.insert(tbname, null, cv);
        return i;
    }

    public String[] getnames() {
        int i = 0;
        String cols[] = {Name, CNumber};
        Cursor cr = db.query(tbname, cols, null, null, null, null, null);
        while ((cr.moveToNext() && (i < 5))) {
            names[i] = cr.getString(0);
            numbers[i] = cr.getString(1);
            i++;
        }
        return names;
    }

    public String[] getnumbers() {
        return numbers;
    }

    public void clearDatabase() {
        db.execSQL("DELETE FROM " + tbname);
        Toast.makeText(context, "Contact Entries Deleted!", Toast.LENGTH_SHORT).show();
    }

    public void deletecontact(String contactnumber) {
        db.execSQL("DELETE FROM " + tbname + " where " + CNumber + " = '" + contactnumber + "'");
        Toast.makeText(context, "Contact Removed", Toast.LENGTH_SHORT).show();
    }
}
