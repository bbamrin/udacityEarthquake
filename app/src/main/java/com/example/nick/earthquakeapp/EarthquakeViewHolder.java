package com.example.nick.earthquakeapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
    TextView dateTextView;
    TextView directionTextView;
    TextView magnitudeTextView;
    TextView exactPlaceTextView;
    TextView timeTextView;




    public EarthquakeViewHolder(final View itemView, final OnItemClickListener clickListener) {
        super(itemView);
        timeTextView = (TextView) itemView.findViewById(R.id.time);
        exactPlaceTextView = (TextView) itemView.findViewById(R.id.primary_location);
        dateTextView = (TextView) itemView.findViewById(R.id.date);
        directionTextView = (TextView) itemView.findViewById(R.id.location_offset);
        magnitudeTextView = (TextView) itemView.findViewById(R.id.magnitude);
        if (clickListener != null) {
            Log.d("LOGT", "sdfg");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(getAdapterPosition(), itemView);
                }
            });
        }
    }
}
