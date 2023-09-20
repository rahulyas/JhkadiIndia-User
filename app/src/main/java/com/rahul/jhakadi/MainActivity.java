package com.rahul.jhakadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rahul.jhakadi.Adapter.BestsellerAdapter;
import com.rahul.jhakadi.Adapter.CategoriesAdapter;
import com.rahul.jhakadi.Model.BestsellerModel;
import com.rahul.jhakadi.Model.CategoriesModel;
import com.rahul.jhakadi.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    ArrayList<CategoriesModel> categoriesList;
    ArrayList<BestsellerModel> bestsellerList;
    BestsellerAdapter bestsellerAdapter;
    CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoriesList = new ArrayList<>();
        bestsellerList = new ArrayList<>();

        categoriesList.add(new CategoriesModel("","A"));
        categoriesList.add(new CategoriesModel("","B"));
        categoriesList.add(new CategoriesModel("","C"));
        categoriesList.add(new CategoriesModel("","D"));
        categoriesList.add(new CategoriesModel("","E"));

        bestsellerList.add(new BestsellerModel("","A","B","50"));
        bestsellerList.add(new BestsellerModel("","B","D","25"));
        bestsellerList.add(new BestsellerModel("","C","E","15"));

        bestsellerAdapter = new BestsellerAdapter(MainActivity.this,bestsellerList);
        categoriesAdapter = new CategoriesAdapter(MainActivity.this,categoriesList);

        binding.categoriesrecycleview.setAdapter(categoriesAdapter);
        binding.bestsellersrecycleview.setAdapter(bestsellerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,1, RecyclerView.HORIZONTAL,false);
        binding.categoriesrecycleview.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(MainActivity.this,1);
        binding.bestsellersrecycleview.setLayoutManager(gridLayoutManager1);
    }
}