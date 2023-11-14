package com.example.sample.catalogClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.DownloadImageTask;
import com.example.sample.R;
import com.example.sample.question_classes.QuestionActivity;

import java.util.List;

public class CatalogStateAdapter extends RecyclerView.Adapter<CatalogStateAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<CatalogState> states;

    public CatalogStateAdapter(Context context, List<CatalogState> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.catalog_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CatalogState state = states.get(position);
        Context context = holder.itemView.getContext();
        //holder.st1.setText(state.getSt1());
        holder.head.setText(state.getHead());

        new DownloadImageTask(holder.img).execute(state.getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(state.getUrl());
                holder.intent.putExtra("url", state.getUrl());
                context.startActivity(holder.intent);
            }
        });





    }


    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Intent intent;
        //final TextView st1,st2,st3,st4,st5,st6,st7,st8, count;
        final TextView head;
        ImageView img;

        ViewHolder(View view){
            super(view);
            intent = new Intent(view.getContext(), QuestionActivity.class);
            //count = view.findViewById(R.id.stcount);
            head = view.findViewById(R.id.textView3);

            img = view.findViewById(R.id.imageView);




        }
    }

}
