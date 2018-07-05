package com.example.nick.earthquakeapp;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class EarthquakeRecyclerAdapter extends RecyclerView.Adapter<EarthquakeViewHolder> {

    ArrayList<Earthquake> earthquakeList;
    Context context;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.onItemClickListener = OnItemClickListener;
    }

    public ArrayList<Earthquake> getEarthquakeList() {
        return earthquakeList;
    }

    public void setEarthquakeList(ArrayList<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

    public EarthquakeRecyclerAdapter(ArrayList<Earthquake> earthquakeList, Context context) {
        this.earthquakeList = earthquakeList;
        this.context = context;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView  = inflater.inflate(R.layout.earthquake_item, parent, false);
        EarthquakeViewHolder viewHolder = new EarthquakeViewHolder(itemView,onItemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = earthquakeList.get(position);
        holder.magnitudeTextView.setText(earthquake.getMagnitude());
        holder.directionTextView.setText(earthquake.getDirection());
        holder.exactPlaceTextView.setText(earthquake.getExactPlace());
        holder.dateTextView.setText(earthquake.getDate());
        holder.timeTextView.setText(earthquake.getTime());

        //setting color of the background circle of the magnitude
        ((GradientDrawable)holder.magnitudeTextView.getBackground()).setColor(getMagnitudeColor(earthquake.getMagnitude(),this.context));



    }

    @Override
    public int getItemCount() {
        return earthquakeList.size();
    }

    private int getMagnitudeColor(String magnitude,Context context){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(Double.parseDouble(magnitude));

        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;

        }

        return ContextCompat.getColor(context,magnitudeColorResourceId);

    }
}
