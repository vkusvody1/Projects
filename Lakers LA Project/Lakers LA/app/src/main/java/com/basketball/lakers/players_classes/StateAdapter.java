package com.basketball.lakers.players_classes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.basketball.lakers.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    public String[] mColors = {"#fafafa","#ffffff"};
    private final ArrayList<State> states;

    StateAdapter(Context context, ArrayList<State> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        State state = states.get(position);
        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]));
        holder.textQuestion.setText(state.getHeader());
        holder.textAnswer.setText(state.getText());
        Glide.with(holder.imageView.getContext())
                .load(state.getImg())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView textQuestion, textAnswer;
        ViewHolder(View view){
            super(view);
            textQuestion = view.findViewById(R.id.textViewQuestion);
            textAnswer = view.findViewById(R.id.textViewAnswer);
            imageView = view.findViewById(R.id.imageViewInfo);


        }
    }

}
