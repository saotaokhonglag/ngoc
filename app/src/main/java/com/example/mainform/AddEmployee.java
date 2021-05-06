package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class AddEmployee extends AppCompatActivity {
    EditText MaNV, Ten, Email, SDT;
    RadioButton rd_gt;
    RadioGroup r_gioitinh;
    Spinner TenPB;
    Button add;
    DBHelper DB;
    String arrMa[], arrTen[];
    SQLiteDatabase sqlite;
    int TrangThai = 1;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        MaNV = (EditText) findViewById(R.id.MaNV);
        TenPB = (Spinner) findViewById(R.id.tenpb);
        Ten = (EditText) findViewById(R.id.Ten);
        Email = (EditText) findViewById(R.id.email);
        SDT = (EditText) findViewById(R.id.sdt);
        add = (Button) findViewById(R.id.add);
        r_gioitinh = findViewById(R.id.radioGroup);
        DB = new DBHelper(this);
        sqlite = openOrCreateDatabase("QLNS.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c = sqlite.rawQuery("Select MaPB,TenPB from PhongBan", null);
        arrMa = new String[c.getCount()];
        arrTen = new String[c.getCount()];
        c.moveToFirst();
        for (int i = 0; i < arrMa.length; i++) {
            arrMa[i] = c.getString(0);
            arrTen[i] = c.getString(1);
            c.moveToNext();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrTen);
        TenPB.setAdapter(arrayAdapter);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int r_id = r_gioitinh.getCheckedRadioButtonId();
                    rd_gt =findViewById(r_id);
                    String Manv = MaNV.getText().toString().trim();
                    String Tenpb = TenPB.getSelectedItem().toString().trim();
                    String ten = Ten.getText().toString().trim();
                    String email = Email.getText().toString().trim();
                    String sdt = SDT.getText().toString().trim();

                    if (Manv.equals("") || ten.equals("") || email.equals("") || sdt.equals("") || r_id==-1) {
                        Toast.makeText(AddEmployee.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean checkManv = DB.checkMaNV(Manv);
                        if (checkManv == false) {
                            String gt = rd_gt.getText().toString().trim();
                            Boolean insert = DB.insertNhanVien(Manv, ten, Tenpb, email, gt, sdt, TrangThai);
                            if (insert == true) {
                                Toast.makeText(AddEmployee.this, "Thêm nhân viên thành công!", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(getApplicationContext(), Employee.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(AddEmployee.this, "Thêm nhân viên không thành công!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AddEmployee.this, "Mã nhân viên này đã tồn tại!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }
    }