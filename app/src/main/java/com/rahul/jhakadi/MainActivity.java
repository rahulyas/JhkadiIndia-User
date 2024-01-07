package com.rahul.jhakadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.rahul.jhakadi.Adapter.BestsellerAdapter;
import com.rahul.jhakadi.Adapter.CategoriesAdapter;
import com.rahul.jhakadi.Adapter.FragmentAdapter;
import com.rahul.jhakadi.Expanded.ExpandedMenuAdapter;
import com.rahul.jhakadi.Expanded.ExpandedMenuModel;
import com.rahul.jhakadi.Fragment.CartFragment;
import com.rahul.jhakadi.Fragment.HomeFragment;
import com.rahul.jhakadi.Model.BestsellerModel;
import com.rahul.jhakadi.Model.CategoriesModel;
import com.rahul.jhakadi.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, 0, 0);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navView = findViewById(R.id.nav_view);
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new CartFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(MainActivity.this,fragmentArrayList);
        binding.viewPager.setAdapter(fragmentAdapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.navBottom.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        binding.navBottom.setSelectedItemId(R.id.cart);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        binding.navBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (item.getItemId()){
                    case R.id.navigation_item1:
                        binding.viewPager.setCurrentItem(0);
                        Toast.makeText(MainActivity.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item2:
                        binding.viewPager.setCurrentItem(1);
                        Toast.makeText(MainActivity.this, "Cart Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        binding.viewPager.setCurrentItem(0);
                        Toast.makeText(MainActivity.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cart:
                        binding.viewPager.setCurrentItem(1);
                        Toast.makeText(MainActivity.this, "Cart Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }

}