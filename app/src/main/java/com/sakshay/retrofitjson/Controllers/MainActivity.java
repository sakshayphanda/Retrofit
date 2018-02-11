package com.sakshay.retrofitjson.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sakshay.retrofitjson.R;
import com.sakshay.retrofitjson.RESTapi.ApiClient;
import com.sakshay.retrofitjson.RESTapi.ApiInterface;
import com.sakshay.retrofitjson.adapter.MoviesAdapter;
import com.sakshay.retrofitjson.model.MovieResponse;
import com.sakshay.retrofitjson.model.MovieResultData;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);


            if (API_KEY.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
                return;
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));


            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    int statusCode = response.code();
                    List<MovieResultData> movies = response.body().getResults();
                    recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                }
                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }
    }
