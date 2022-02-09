package com.example.patientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddpatientActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    AppCompatButton b1;
    String getPatientCode,getPatientName,getAddress,getMobileNo,getDoctorName;
    Dbtext mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient);
        ed1=(EditText) findViewById(R.id.pcode);
        ed2=(EditText) findViewById(R.id.pname);
        ed3=(EditText) findViewById(R.id.paddress);
        ed4=(EditText) findViewById(R.id.pmobile);
        ed5=(EditText) findViewById(R.id.dname);
        mydb=new Dbtext(this);
        mydb.getWritableDatabase();
        b1=(AppCompatButton)findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPatientCode=ed1.getText().toString();
                getPatientName=ed2.getText().toString();
                getAddress=ed3.getText().toString();
                getMobileNo=ed4.getText().toString();
                getDoctorName=ed5.getText().toString();

                boolean status=mydb.insertpatient(getPatientCode,getPatientName,getAddress,getMobileNo,getDoctorName);
                if (status==true)
                {
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    ed5.setText("");
                    Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Something Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}