package com.asifshaikh.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Network.NetworkInterface;
import Network.RetroFitClient;
import NetworkPojo.NewsData;
import NetworkPojo.NewsModel;
import adapter.NewsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    List<NewsData> newsDataList;
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    private static final String API_KEY = "c50ed3ae0d0e4e9ab41adf7feccfb998";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsDataList=new ArrayList<>();
        recyclerView = findViewById(R.id.newsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//         newsAdapter=new NewsAdapter(newsDataList);
        recyclerView.setAdapter(newsAdapter);

        getCloudNews();

    }

    void getCloudNews(){
        Retrofit retrofit = RetroFitClient.getRetrofit();
        final NetworkInterface newsApi = retrofit.create(NetworkInterface.class);

        Call<NewsModel> call=newsApi.getLatestNews("us","business",API_KEY);

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                newsDataList.clear();
                if (response.body() != null) {
                    if (response.isSuccessful()&& response.body().getArticles()!=null) {

                        Log.d("TAG", "On MY RESPONSE" + response.body().getStatus());
                        Toast.makeText(MainActivity.this,response.body().getTotalResults(), Toast.LENGTH_LONG).show();


                        if (!newsDataList.isEmpty()){
                            newsDataList.clear();
                        }

                        newsDataList = response.body().getArticles();
                        newsAdapter = new NewsAdapter(newsDataList);
                        recyclerView.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();

//                        for (NewsData d : response.body().getArticles()
//                        ) {
//                            Log.d("TAG", "On MY RESPONSE" + response.body().getArticles());
//                            newsDataList.add(d);
//                        }
////                    }
//                        newsAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.e("error", t.toString());
            }
        });


    }
}