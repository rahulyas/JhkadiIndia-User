package com.rahul.jhakadi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.rahul.jhakadi.Adapter.BestsellerAdapter;
import com.rahul.jhakadi.Adapter.CategoriesAdapter;
import com.rahul.jhakadi.Expanded.ExpandedMenuAdapter;
import com.rahul.jhakadi.Expanded.ExpandedMenuModel;
import com.rahul.jhakadi.Model.BestsellerModel;
import com.rahul.jhakadi.Model.CategoriesModel;
import com.rahul.jhakadi.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    ArrayList<ExpandedMenuModel> headerList = new ArrayList<>();
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

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setDrawerLayout(binding.drawerLayout)
                .build();

        prepareListData();

        expandedMenuAdapter = new ExpandedMenuAdapter(this, headerList, childList, binding.expandedListView);
        binding.expandedListView.setAdapter(expandedMenuAdapter);
        binding.expandedListView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);

        binding.expandedListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        //navController.navigate(R.id.nav_changeproject);
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
                        }*/
                        break;

                    case 1:
                        Toast.makeText(MainActivity.this, "Second", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });



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