package com.sport.fitnesshelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private ArrayList<FoodItem> foodItems;

    public FoodAdapter(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.nameTextView.setText(foodItem.getName());
        holder.caloriesTextView.setText("Калории: " + foodItem.getCalories());
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView caloriesTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            caloriesTextView = itemView.findViewById(R.id.caloriesTextView);
        }
    }
}
