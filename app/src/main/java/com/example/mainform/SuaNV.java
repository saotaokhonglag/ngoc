package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SuaNV extends AppCompatActivity {
    EditText Ten, Email, SDT, NgaySinh;
    RadioButton rd_gt;
    RadioGroup r_gioitinh;
    Spinner TenPB, TT;
    Button Sua, Xoa;
    SQLiteDatabase sqlite;
    String arrMa[], arrTen[];
    String[] arrTT = new String[]{"Còn làm","Nghỉ làm"};
    String manv, ten, email, tenpb, sdt, ngaysinh, tt;
    int trangthai = 1;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_n_v);
        DBHelper DB = new DBHelper(this);
        AddEmployee addEmployee = new AddEmployee();
        Ten = findViewById(R.id.TenNV2);
        Email = findViewById(R.id.email2);
        TenPB = findViewById(R.id.TenPB2);
        TT = findViewById(R.id.TT);
        SDT = findViewById(R.id.sdt2);
        NgaySinh = findViewById(R.id.ngaysinh);
        Sua = findViewById(R.id.Sua);
        Xoa = findViewById(R.id.xoa);
        r_gioitinh = findViewById(R.id.radioGroup);
        sqlite = openOrCreateDatabase("QLNS.db", SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c = sqlite.rawQuery("Select MaPB,TenPB from PhongBan", null);
        arrMa = new String[c.getCount()];
        arrTen = new String[c.getCount()];
        c.moveToFirst();
        for (int i = 0; i < arrMa.length; i++) {
            arrMa[i] = c.getString(0);
            arrTen[i] = c.getString(1);
            c.moveToNext();
        }

        ArrayAdapter PBAdapter=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,arrTen);
        TenPB.setAdapter(PBAdapter);

        ArrayAdapter TTap = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrTT);
        TT.setAdapter(TTap);

        NgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee.chonngay();
            }
        });
        Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r_id = r_gioitinh.getCheckedRadioButtonId();
                rd_gt =findViewById(r_id);
                String tennv = Ten.getText().toString().trim();
                String tenPB = TenPB.getSelectedItem().toString().trim();
                String mail = Email.getText().toString().trim();
                String sdtNV = SDT.getText().toString().trim();
                String gt = rd_gt.getText().toString().trim();
                String ns = NgaySinh.getText().toString().trim();
                if(TT.getSelectedItem().toString().trim()=="Còn làm"){
                    trangthai = 1;
                }else trangthai=0;
                DB.updateNV(manv, tennv, tenPB, mail, gt, sdtNV, ns, trangthai);
                Toast.makeText(SuaNV.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent =  new Intent(getApplicationContext(), Employee.class);
                startActivity(intent);
            }
        });
        Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlite.execSQL("Update NhanVien SET TrangThai=0 where MaNV=?", new String[]{manv});
                Toast.makeText(SuaNV.this, "Xóa nhân viên thành công!", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent =  new Intent(getApplicationContext(), Employee.class);
                startActivity(intent);

            }
        });
        getAndSetIntendata();
    }
    void getAndSetIntendata(){
        if(getIntent().hasExtra("MaNV") && getIntent().hasExtra("Ten") && getIntent().hasExtra("TenPB")
                && getIntent().hasExtra("Email") && getIntent().hasExtra("SDT")&& getIntent().hasExtra("NgaySinh")){
                //Getting data from Intent
                manv = getIntent().getStringExtra("MaNV");
                ten = getIntent().getStringExtra("Ten");
                tenpb = getIntent().getStringExtra("TenPB");
                email = getIntent().getStringExtra("Email");
                sdt = getIntent().getStringExtra("SDT");
                ngaysinh = getIntent().getStringExtra("NgaySinh");
                // Setting intent data
                Ten.setText(ten);
                Email.setText(email);
                SDT.setText(sdt);
                NgaySinh.setText(ngaysinh);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}