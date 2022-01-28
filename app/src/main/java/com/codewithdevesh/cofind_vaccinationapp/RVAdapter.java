package com.codewithdevesh.cofind_vaccinationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RVModel>arrayList;

    public RVAdapter(Context context, ArrayList<RVModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        RVModel model = arrayList.get(position);
        holder.centerName.setText(model.getCenterName());
        holder.centerLoc.setText(model.getCenterAddress());
        holder.centerTime.setText("From - " +model.timingsFrom + " to - " + model.timingsTo);
        holder.vaccName.setText(model.getVaccineName());
        holder.vaccPrice.setText(model.getFee());
        holder.ageLimit.setText("Age - "+ model.getAgeLimit());
        holder.avail.setText("Availability - "+model.getCapacity());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView centerName,centerLoc,centerTime,vaccName,vaccPrice,ageLimit,avail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            centerName = itemView.findViewById(R.id.tv_centerName);
            centerLoc = itemView.findViewById(R.id.tv_centerLocation);
            centerTime = itemView.findViewById(R.id.tv_timings);
            vaccName = itemView.findViewById(R.id.tv_vaccine_name);
            vaccPrice = itemView.findViewById(R.id.tv_vaccine_price);
            ageLimit = itemView.findViewById(R.id.tv_age_limits);
            avail = itemView.findViewById(R.id.tv_availability);

        }
    }
}
