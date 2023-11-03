package com.sport.runningtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RunAdapter extends ArrayAdapter<Run> {

    public RunAdapter(Context context, ArrayList<Run> runs) {
        super(context, 0, runs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Run run = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.run_item, parent, false);
        }

        TextView distanceView = convertView.findViewById(R.id.distance_text);
        TextView timeView = convertView.findViewById(R.id.time_text);

        distanceView.setText(run.getDistance());
        timeView.setText(run.getTime());

        return convertView;
    }
}
