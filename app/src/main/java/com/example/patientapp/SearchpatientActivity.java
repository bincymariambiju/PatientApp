package com.example.patientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchpatientActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    AppCompatButton b1;
    String getPatientCode,getPatientName,getAddress,getMobileNo,getDoctorName;
    Dbtext mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpatient);
        ed1=(EditText) findViewById(R.id.pcode);
        ed2=(EditText) findViewById(R.id.pname);
        ed3=(EditText) findViewById(R.id.paddress);
        ed4=(EditText) findViewById(R.id.pmobile);
        ed5=(EditText) findViewById(R.id.dname);
        b1=(AppCompatButton) findViewById(R.id.srch) ;
        mydb=new Dbtext(this);
        mydb.getWritableDatabase();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMobileNo=ed4.getText().toString();
                Cursor c=mydb.SearchPatient(getMobileNo);
                if (c.getCount()==0)
                {
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    ed5.setText("");
                    Toast.makeText(getApplicationContext(), "No Patient Found", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    while (c.moveToNext())
                    {
                        getPatientCode=c.getString(1);
                        getPatientName=c.getString(2);
                        getAddress=c.getString(3);
                        getDoctorName=c.getString(5);
                    }
                    ed1.setText(getPatientCode);
                    ed2.setText(getPatientName);
                    ed3.setText(getAddress);
                    ed5.setText(getDoctorName);
                }
            }
        });
    }
}