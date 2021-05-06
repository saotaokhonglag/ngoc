package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhongBan extends AppCompatActivity {
    EditText MaPB, TenPB, SDT;
    Button Add;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phong_ban);
        DB = new DBHelper(this);

        MaPB = findViewById(R.id.MaPB);
        TenPB = findViewById(R.id.TenPB);
        SDT = findViewById(R.id.SDT);
        Add = findViewById(R.id.add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapb = MaPB.getText().toString().trim();
                String tenpb = TenPB.getText().toString().trim();
                String sdt = SDT.getText().toString().trim();

                if(mapb.equals("")||tenpb.equals("")||sdt.equals("")){
                    Toast.makeText(AddPhongBan.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if(DB.checkMaPB(mapb)==false){
                        Boolean insert = DB.insertPhongBan(mapb, tenpb, sdt);
                        if(insert==true){
                            Toast.makeText(AddPhongBan.this, "Thêm phòng ban thành công", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), AddPhongBan.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(AddPhongBan.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(AddPhongBan.this, "Mã phòng ban đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}