package com.rahul.jhakadi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahul.jhakadi.Model.CategoriesModel;
import com.rahul.jhakadi.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<CategoriesModel> categoriesList;

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_categories,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.MyViewHolder holder, int position) {
        holder.categories_name.setText(categoriesList.get(position).getName());
        Glide.with(context).load(categoriesList.get(position).getDataImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public void searchDataList(ArrayList<CategoriesModel> searchlist){
        categoriesList = searchlist;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView categories_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.categoriesViewImage);
            categories_name = itemView.findViewById(R.id.categoriesViewName);
        }
    }
}
