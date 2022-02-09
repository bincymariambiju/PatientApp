package com.example.patientapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbtext extends SQLiteOpenHelper {
    static String Dbname="PatientApp.db";
    static String Tablename="patient";
    static String col1="id";
    static String col2="Patientcode";
    static String col3="Patientname";
    static String col4="address";
    static String col5="mobile";
    static String col6="doctorname";
    public Dbtext(Context context) {
        super(context,Dbname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+Tablename+"("+col1+
                " integer primary key autoincrement,"+col2+
                " text,"+col3+" text,"+col4+" text,"+col5+
                " text,"+col6+" text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="drop table if exists "+Tablename;
        db.execSQL(query);
        onCreate(db);
    }
    public boolean insertpatient(String PatientCode,String PatientName,String Address,String MobileNo,String DoctorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Content = new ContentValues();
        Content.put(col2, PatientCode);
        Content.put(col3, PatientName);
        Content.put(col4, Address);
        Content.put(col5, MobileNo);
        Content.put(col6, DoctorName);
        long status = db.insert(Tablename, null, Content);
        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor SearchPatient(String MobileNo)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+Tablename+" where "+
                col5+"="+"'"+MobileNo+"'";
        Cursor c=db.rawQuery(query,null);
        return c;
    }
}

