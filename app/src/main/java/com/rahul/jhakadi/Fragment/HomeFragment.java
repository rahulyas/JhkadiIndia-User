package com.rahul.jhakadi.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rahul.jhakadi.Adapter.BestsellerAdapter;
import com.rahul.jhakadi.Adapter.CategoriesAdapter;
import com.rahul.jhakadi.Adapter.FragmentAdapter;
import com.rahul.jhakadi.Model.BestsellerModel;
import com.rahul.jhakadi.Model.CategoriesModel;
import com.rahul.jhakadi.R;
import com.rahul.jhakadi.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    ArrayList<CategoriesModel> categoriesList;
    ArrayList<BestsellerModel> bestsellerList;
    BestsellerAdapter bestsellerAdapter;
    CategoriesAdapter categoriesAdapter;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
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

        bestsellerAdapter = new BestsellerAdapter(requireContext(),bestsellerList);
        categoriesAdapter = new CategoriesAdapter(requireContext(),categoriesList);

        binding.categoriesrecycleview.setAdapter(categoriesAdapter);
        binding.bestsellersrecycleview.setAdapter(bestsellerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, RecyclerView.HORIZONTAL,false);
        binding.categoriesrecycleview.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(),1);
        binding.bestsellersrecycleview.setLayoutManager(gridLayoutManager1);

        return view;
    }

}