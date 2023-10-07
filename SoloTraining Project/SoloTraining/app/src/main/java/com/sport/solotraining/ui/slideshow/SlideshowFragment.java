package com.sport.solotraining.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sport.solotraining.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    public View onStartView() {


        public class MainActivity extends AppCompatActivity {

            private List<Exercise> exerciseList = new ArrayList<>();
            private ExerciseAdapter exerciseAdapter;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                // Инициализация RecyclerView
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                // Инициализация адаптера
                exerciseAdapter = new ExerciseAdapter(this, exerciseList);
                recyclerView.setAdapter(exerciseAdapter);

                // Загрузка упражнений с сервера
                loadExercisesFromServer();
            }

            private void loadExercisesFromServer() {
                // Загрузка JSON из файла на сервере
                String json = loadJSONFromAsset(); // Замените эту строку на метод загрузки с сервера

                if (json != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(json);

                        // Парсинг JSON
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");

                            Exercise exercise = new Exercise(name, description);
                            exerciseList.add(exercise);
                        }

                        // Уведомление адаптера о добавлении данных
                        exerciseAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            private String loadJSONFromAsset() {
                String json = null;
                try {
                    InputStream is = getAssets().open("exercises.json"); // Замените на метод загрузки с сервера
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return json;
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}