package com.sport.solotraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public class ExerciseAdapter extends BaseAdapter {
    private Context context;
    private List<Exercise> exerciseList;

    public ExerciseAdapter(Context context, List<Exercise> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_exercise, parent, false);
        }

        Exercise exercise = exerciseList.get(position);

        TextView exerciseName = view.findViewById(R.id.exercise_name);
        exerciseName.setText(exercise.getName());

        EditText progressInput = view.findViewById(R.id.progress_input);
        progressInput.setHint("Прогресс");

        Button recordButton = view.findViewById(R.id.record_progress_button);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка нажатия кнопки
            }
        });

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress(exercise.getProgress());

        return view;
    }
}
