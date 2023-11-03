package com.sport.footballnews;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApiService service = retrofit.create(NewsApiService.class);

        Call<NewsResponse> call = service.getFootballNews("football", "57b7214904b3429c869d0403ae6c342a");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    List<NewsArticle> articles = response.body().getArticles();

                    StringBuilder newsText = new StringBuilder();

                    for (NewsArticle article : articles) {
                        newsText.append(article.getTitle()).append("\n")
                                .append(article.getDescription()).append("\n")
                                .append(article.getUrl()).append("\n\n");
                    }

                    newsTextView.setText(newsText.toString());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsTextView.setText("Error fetching news");
            }
        });
    }
}
