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

public class CusPB extends RecyclerView.Adapter<CusPB.MyViewHolder> {
    ArrayList MaPB;
    ArrayList TenPB;
    ArrayList SDT;
    Context context;

    CusPB(Context context, ArrayList MaPB, ArrayList TenPB, ArrayList SDT) {
        this.context = context;
        this.MaPB = MaPB;
        this.TenPB = TenPB;
        this.SDT = SDT;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_pb, parent, false);
        return new CusPB.MyViewHolder(view);
        }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mapb.setText(String.valueOf(MaPB.get(position)));
        holder.tenpb.setText(String.valueOf(TenPB.get(position)));
        holder.sdt.setText(String.valueOf(SDT.get(position)));
        holder.mainlayoutPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuaPB.class);
                intent.putExtra("MaPB",String.valueOf(MaPB.get(position)));
                intent.putExtra("TenPB",String.valueOf(TenPB.get(position)));
                intent.putExtra("SDT",String.valueOf(SDT.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MaPB.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mapb, tenpb, sdt;
        LinearLayout mainlayoutPB;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mapb = itemView.findViewById(R.id.ma_pb2);
            tenpb = itemView.findViewById(R.id.ten_pb2);
            sdt = itemView.findViewById(R.id.sdt_pb2);
            mainlayoutPB = itemView.findViewById(R.id.mainlayoutPB);
        }
    }
}
