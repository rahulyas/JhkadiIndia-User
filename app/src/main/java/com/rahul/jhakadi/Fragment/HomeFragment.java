package com.rahul.jhakadi.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rahul.jhakadi.Adapter.FragmentAdapter;
import com.rahul.jhakadi.R;
import com.rahul.jhakadi.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

/*        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),getLifecycle());
        binding.viewPager.setAdapter(fragmentAdapter);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.navigation_item1){
                    binding.viewPager.setCurrentItem(0, true);
                    return true;
                }else if(id == R.id.navigation_item2){
                    binding.viewPager.setCurrentItem(1, true);
                    return true;
                }
*//*                switch (item.getItemId()) {
                    case R.id.navigation_item1:
                        binding.viewPager.setCurrentItem(0, true);
                        return true;
                    case R.id.navigation_item2:
                        binding.viewPager.setCurrentItem(1, true);
                        return true;
                    // Add more cases as needed for each menu item
                }*//*
                return false;
            }
        });

        // Add a ViewPager2 page change listener to update the selected item in BottomNavigationView
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.bottomNavigationView.setSelectedItemId(R.id.navigation_item1);
                        break;
                    case 1:
                        binding.bottomNavigationView.setSelectedItemId(R.id.navigation_item2);
                        break;
                    // Update for additional pages
                }
            }
        });*/
        return view;
    }

}