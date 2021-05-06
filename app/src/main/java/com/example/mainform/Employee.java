package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Employee extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper DB;
    ArrayList<String> MaNV, Ten, TenPB, Email, GioiTinh, SDT;
    CustomNV customNV;
    SQLiteDatabase sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        FloatingActionButton add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuaNV.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddEmployee.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(this);

        MaNV = new ArrayList<>();
        Ten = new ArrayList<>();
        TenPB = new ArrayList<>();
        Email = new ArrayList<>();
        GioiTinh = new ArrayList<>();
        SDT = new ArrayList<>();

        displayData();

        customNV = new CustomNV(Employee.this, MaNV, Ten, TenPB, Email, GioiTinh, SDT);
        recyclerView.setAdapter(customNV);
        recyclerView.setLayoutManager(new LinearLayoutManager(Employee.this));
    }
    void displayData(){
        Cursor cursor = DB.readNhanVien();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                MaNV.add(cursor.getString(0));
                Ten.add(cursor.getString(1));
                TenPB.add(cursor.getString(2));
                Email.add(cursor.getString(3));
                GioiTinh.add(cursor.getString(4));
                SDT.add(cursor.getString(5));
            }
        }
    }
}