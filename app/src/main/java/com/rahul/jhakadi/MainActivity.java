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
    private AppBarConfiguration appBarConfiguration;
    ArrayList<ExpandedMenuModel> headerList = new ArrayList<>();
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    HashMap<ExpandedMenuModel, ArrayList<String>> childList = new HashMap<>();
    NavController navController;
    ExpandedMenuAdapter expandedMenuAdapter;

    ViewGroup viewGroup;
    ArrayList<CategoriesModel> categoriesList;
    ArrayList<BestsellerModel> bestsellerList;
    BestsellerAdapter bestsellerAdapter;
    CategoriesAdapter categoriesAdapter;
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
                int id = item.getItemId();
                if(id == R.id.home){
                    Toast.makeText(MainActivity.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }else if(id == R.id.cart){
                    Toast.makeText(MainActivity.this, "Cart Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return true;
            }
        });

/*        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setDrawerLayout(binding.drawerLayout)
                .build();*/
/*        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,)
        prepareListData();*/

/*
        expandedMenuAdapter = new ExpandedMenuAdapter(this, headerList, childList, binding.expandedListView);
        binding.expandedListView.setAdapter(expandedMenuAdapter);
        binding.expandedListView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);

        binding.expandedListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        //navController.navigate(R.id.nav_changeproject);
                        */
/*if (parent.isGroupExpanded(groupPosition))
                        {
                            parent.collapseGroup(groupPosition);
                        }
                        else {
                            parent.expandGroup(groupPosition);
                            parent.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                @Override
                                public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                                    view.setSelected(true);
                                    if (viewGroup != null) {
                                        viewGroup.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                    }
                                    viewGroup = (ViewGroup) view;
                                    viewGroup.setBackgroundColor(Color.parseColor("#2ba89c"));

                                    binding.drawerLayout.closeDrawer(GravityCompat.START);

                                    switch (childPosition) {
                                        case 0:
                                            navController.navigate(R.id.nav_changeproject);
                                            break;
                                        case 1:
                                            String[] items = {"A", "B"};
                                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                            builder.setTitle("Select");
                                            builder.setItems(items, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    switch (which) {
                                                        case 0:
                                                            navController.navigate(R.id.nav_a);
                                                            break;
                                                        case 1:
                                                            navController.navigate(R.id.nav_b);
                                                            break;
                                                    }
                                                }
                                            });
                                            builder.show();
                                            break;
                                        // case 1:
                                        //     navController.navigate(R.id.nav_secondChild);
                                        //     break;
                                    }
                                    parent.expandGroup(groupPosition);
                                    return true;
                                }
                            });
                        }*//*

                        break;

                    case 1:
                        Toast.makeText(MainActivity.this, "Second", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
*/


/*
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
*/

    }

    private void prepareListData() {

        ExpandedMenuModel headerFirst = new ExpandedMenuModel();
        headerFirst.setItemName("Home");
        // headerFirst.setItemIcon(R.drawable.ic_menu_camera);

        headerList.add(headerFirst);

        ExpandedMenuModel headerSecond = new ExpandedMenuModel();
        headerSecond.setItemName("B");
        // headerSecond.setItemIcon(R.drawable.ic_menu_camera);

        headerList.add(headerSecond);

        ExpandedMenuModel headerThird = new ExpandedMenuModel();
        headerThird.setItemName("C");
        headerList.add(headerThird);

/*
        ArrayList<String> childFirst = new ArrayList<>();
        childFirst.add("A");
        childFirst.add("B");
        childFirst.add("C");

        ArrayList<String> childSecond = new ArrayList<>();
        childSecond.add("D");
        childSecond.add("E");
        childSecond.add("F");

        childList.put(headerList.get(0), childFirst);
        childList.put(headerList.get(1), childSecond);
*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

}