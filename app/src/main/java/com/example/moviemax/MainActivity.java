package com.example.moviemax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShowAdapter showAdapter;
    private ArrayList<Show> showArrayList;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON(){
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=ee960f573833509472cb7ab57f055c12&language=en-US&page=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String title = hit.getString("title");
                                String showImage = "https://image.tmdb.org/t/p/w500" + hit.getString("poster_path");



                                showArrayList.add(new Show(title, showImage));

                            }

                            showAdapter = new ShowAdapter(MainActivity.this, showArrayList);
                            recyclerView.setAdapter(showAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}
