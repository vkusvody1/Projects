package com.sport.fitnesshelper.list_classes;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sport.fitnesshelper.R;

import java.io.File;
import java.util.List;


public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    public String[] mColors = {"#fafafa","#ffffff"};
    private final List<State> states;

    public StateAdapter(Context context, List<State> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        State state = states.get(position);
        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]));
        holder.name.setText(state.getName());
        holder.date.setText(state.getDate());
        holder.kkal.setText(state.getKkal());
        File b = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), state.getUri());



    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView name, date, kkal;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.textView2);
            date = view.findViewById(R.id.textView4);
            kkal = view.findViewById(R.id.textView3);



        }
    }
}