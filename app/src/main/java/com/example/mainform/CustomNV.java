package com.example.mainform;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomNV extends RecyclerView.Adapter<CustomNV.MyViewHolder> {
    ArrayList MaNV;
    ArrayList Ten;
    ArrayList TenPB;
    ArrayList Email;
    ArrayList GioiTinh;
    ArrayList SDT;
    ArrayList NgaySinh;
    int TrangThai;
    Context context;

    CustomNV(Context context, ArrayList MaNV, ArrayList Ten, ArrayList TenPB, ArrayList Email, ArrayList GioiTinh, ArrayList SDT, ArrayList NgaySinh){
        this.context = context;
        this.MaNV = MaNV;
        this.Ten = Ten;
        this.TenPB = TenPB;
        this.Email = Email;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.NgaySinh = NgaySinh;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_nv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Ma_nv.setText(String.valueOf(MaNV.get(position)));
        holder.Ten_nv.setText(String.valueOf(Ten.get(position)));
        holder.Ten_pb.setText(String.valueOf(TenPB.get(position)));
        holder.email_nv.setText(String.valueOf(Email.get(position)));
        holder.gioitinh_nv.setText(String.valueOf(GioiTinh.get(position)));
        holder.sdt_nv.setText(String.valueOf(SDT.get(position)));
        holder.ns.setText(String.valueOf(NgaySinh.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuaNV.class);
                intent.putExtra("MaNV",String.valueOf(MaNV.get(position)));
                intent.putExtra("Ten",String.valueOf(Ten.get(position)));
                intent.putExtra("TenPB",String.valueOf(TenPB.get(position)));
                intent.putExtra("Email",String.valueOf(Email.get(position)));
                intent.putExtra("GioiTinh",String.valueOf(GioiTinh.get(position)));
                intent.putExtra("SDT",String.valueOf(SDT.get(position)));
                intent.putExtra("NgaySinh",String.valueOf(NgaySinh.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MaNV.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Ma_nv, Ten_nv, Ten_pb, email_nv, gioitinh_nv, sdt_nv, ns;
        LinearLayout mainlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Ma_nv = itemView.findViewById(R.id.Ma_pb2);
            Ten_nv = itemView.findViewById(R.id.Ten_pb2);
            Ten_pb = itemView.findViewById(R.id.Ten_pb);
            email_nv = itemView.findViewById(R.id.email_nv);
            gioitinh_nv = itemView.findViewById(R.id.gioitinh);
            sdt_nv = itemView.findViewById(R.id.sdt_pb);
            ns = itemView.findViewById(R.id.ns);

            mainlayout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
