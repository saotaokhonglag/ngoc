package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Categories extends AppCompatActivity {

    ImageView backBtn;
    Button employee, department, payroll, timesheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        employee = (Button)findViewById(R.id.employee);
        department = (Button)findViewById(R.id.department);
        payroll = (Button)findViewById(R.id.payroll);
        timesheet = (Button)findViewById(R.id.timesh);

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Employee.class);
                startActivity(intent);
            }
        });

        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), department.class);
                startActivity(intent);
            }
        });
        payroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payroll.class);
                startActivity(intent);
            }
        });
        timesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), timesheet.class);
                startActivity(intent);
            }
        });
    }
}