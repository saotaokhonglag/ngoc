package com.example.mainform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "QLNS.db";
    public ArrayList<String> mapb;

    public DBHelper(@Nullable Context context) {
        super(context, "QLNS.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table TaiKhoan(Username TEXT PRIMARY KEY, password TEXT, Email TEXT)");

        MyDB.execSQL("create Table NhanVien(MaNV VARCHAR PRIMARY KEY, Ten TEXT, TenPB TEXT, Email TEXT, GioiTinh TEXT, SDT CHAR, TrangThai INT)");

        MyDB.execSQL("create Table PhongBan(MaPB VARCHAR PRIMARY KEY, TenPB TEXT, SDT CHAR)");

        MyDB.execSQL("create Table Luong(MaLuong INTEGER PRIMARY KEY AUTOINCREMENT, MaNV VARCHAR,TenPB TEXT, " +
                "NgayCong INT, TongLuong FLOAT, ChucVu TEXT)");

        MyDB.execSQL("create Table ChamCong(MaCC INTEGER PRIMARY KEY AUTOINCREMENT, MaNV VARCHAR, " +
                "NghiPhep DATE, NghiKhongPhep DATE, NgayCong INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop table if exists TaiKhoan");

        MyDB.execSQL("drop table if exists NhanVien");

        MyDB.execSQL("drop table if exists PhongBan");

        MyDB.execSQL("drop table if exists Luong");

        MyDB.execSQL("drop table if exists ChamCong");
    }

    Boolean insertData(String Username, String password, String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = MyDB.insert("TaiKhoan", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    Boolean insertNhanVien(String MaNV, String Ten, String TenPB, String email, String GioiTinh,  String SDT, int TrangThai) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaNV", MaNV);
        contentValues.put("Ten", Ten);
        contentValues.put("TenPB", TenPB);
        contentValues.put("email", email);
        contentValues.put("GioiTinh", GioiTinh);
        contentValues.put("SDT", SDT);
        contentValues.put("TrangThai", TrangThai);
        long result = MyDB.insert("NhanVien", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }
    Cursor readNhanVien(){
        String query = "Select  MaNV, Ten, TenPB, Email, GioiTinh, SDT from NhanVien where TrangThai=1";
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = null;
        if(MyDB !=null){
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;
    }
    Boolean updateNV(String MaNV, String Ten, String TenPB, String email, String GioiTinh, String SDT, int TrangThai){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MaNV", MaNV);
        cv.put("Ten", Ten);
        cv.put("TenPB", TenPB);
        cv.put("email", email);
        cv.put("GioiTinh", GioiTinh);
        cv.put("SDT", SDT);
        cv.put("TrangThai", TrangThai);
       long result = MyDB.update("NhanVien", cv, "MaNV=?", new String[]{MaNV});
        if(result==-1){
            return false;
        }else {
            return true; 
        }

    }
    Boolean insertPhongBan(String MaPB, String TenPB, String SDT){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaPB", MaPB);
        contentValues.put("Tenpb", TenPB);
        contentValues.put("SDT", SDT);
        long result = MyDB.insert("PhongBan", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    Cursor readPhongBan(){
        String query = "Select MaPB, TenPB, SDT from PhongBan";
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = null;
        if(MyDB !=null){
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;
    }
    Boolean updatePB(String MaPB, String TenPB, String SDT) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MaNV", MaPB);
        cv.put("TenPB", TenPB);
        cv.put("SDT", SDT);
        long result = MyDB.update("PhongBan", cv, "MaPB=?", new String[]{MaPB});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkusername(String Username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where Username = ?", new String[]{Username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkpassword(String Username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where Username = ? and password = ?", new String[]{Username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkMaNV(String MaNV){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select MaNV from NhanVien where MaNV = ?", new String[]{MaNV});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkMaPB(String MaPB){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select MaPB from PhongBan where MaPB = ?", new String[]{MaPB});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
