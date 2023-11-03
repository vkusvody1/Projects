package com.sport.chicagobulls;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class menuStateAdapter extends RecyclerView.Adapter<menuStateAdapter.ViewHolder>{
    private final LayoutInflater inflater;

    private final List<menuState> states;

    public menuStateAdapter(Context context, List<menuState> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        menuState state = states.get(position);
        holder.header.setText(state.getHeader());
        Glide.with(holder.img.getContext())
                .load(state.getImg1())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , HistoryActivity.class);
                intent.putExtra("header", state.getHeader());
                intent.putExtra("img",state.getImg2());
                intent.putExtra("text",state.getText());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView img;
        final TextView header;
        ViewHolder(View view){
            super(view);

            header = view.findViewById(R.id.tv_header);
            img = view.findViewById(R.id.img1);


        }
    }
}