package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class SuaPB extends AppCompatActivity {
    EditText MaPB, TenPB, SDT;
    String mapb, tenpb, sdt;
    Button sua;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_p_b);
        DB = new DBHelper(this);
        MaPB = findViewById(R.id.MaPB2);
        TenPB = findViewById(R.id.TenPB2);
        SDT = findViewById(R.id.SDT2);
        sua = findViewById(R.id.update);
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maPB = MaPB.getText().toString().trim();
                String tenPB = TenPB.getText().toString().trim();
                String sdt = SDT.getText().toString().trim();
                DB.updatePB(maPB, tenPB, sdt );
                Toast.makeText(SuaPB.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent =  new Intent(getApplicationContext(), Employee.class);
                startActivity(intent);
            }
        });
        getAndSetIntendata();
    }
    void getAndSetIntendata(){
        if(getIntent().hasExtra("MaPB") && getIntent().hasExtra("TenPB")
                && getIntent().hasExtra("SDT")){
            //Getting data from Intent
            mapb = getIntent().getStringExtra("MaPB");
            tenpb = getIntent().getStringExtra("TenPB");
            sdt = getIntent().getStringExtra("SDT");
            // Setting intent data
            MaPB.setText(mapb);
            TenPB.setText(tenpb);
            SDT.setText(sdt);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}