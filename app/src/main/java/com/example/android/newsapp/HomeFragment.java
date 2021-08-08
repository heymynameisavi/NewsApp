package com.example.android.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    String api = "548aa4390bd241009dc6a5bc99026641";

    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewofHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, null);

        recyclerViewofHome = view.findViewById(R.id.recyclerviewofhome);
        modelClassArrayList = new ArrayList<>();
        recyclerViewofHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerViewofHome.setAdapter(adapter);

        findNews();
        return view;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getNews(country, 100, api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {

                if (response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}