package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class department extends AppCompatActivity {
    RecyclerView recyclerViewPB;
    DBHelper DB;
    ArrayList<String> MaPB, TenPB, SDT;
    CusPB customPB;
    SQLiteDatabase sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        FloatingActionButton add_pb = findViewById(R.id.add_pb);
        recyclerViewPB = findViewById(R.id.recycler);
        recyclerViewPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuaPB.class);
                startActivity(intent);
            }
        });
        add_pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPhongBan.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(this);
        MaPB = new ArrayList<>();
        TenPB = new ArrayList<>();
        SDT = new ArrayList<>();

        displayData();

        customPB = new CusPB(department.this, MaPB, TenPB, SDT);
        recyclerViewPB.setAdapter(customPB);
        recyclerViewPB.setLayoutManager(new LinearLayoutManager(department.this));
    }
    void displayData(){
        Cursor cursor = DB.readPhongBan();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                MaPB.add(cursor.getString(0));
                TenPB.add(cursor.getString(1));
                SDT.add(cursor.getString(2));
            }
        }
    }
}