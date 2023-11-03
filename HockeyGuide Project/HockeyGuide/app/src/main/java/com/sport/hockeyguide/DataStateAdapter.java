package com.sport.hockeyguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class DataStateAdapter extends RecyclerView.Adapter<DataStateAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    public String[] mColors = {"#fafafa","#ffffff"};
    private final List<DataState> states;

    DataStateAdapter(Context context, List<DataState> states) {
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
        DataState state = states.get(position);
        holder.header.setText(state.getHeader());

        holder.text.setText(state.getText());

        Glide.with(holder.img.getContext())
                .load(state.getImage())
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView img;
        final TextView header, text;
        ViewHolder(View view){
            super(view);

            header = view.findViewById(R.id.textV_head);
            text = view.findViewById(R.id.textV_about);
            img = view.findViewById(R.id.imageV_info);


        }
    }
}

