package com.rahul.jhakadi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.rahul.jhakadi.Model.BestsellerModel;
import com.rahul.jhakadi.Model.CategoriesModel;
import com.rahul.jhakadi.R;

import java.util.ArrayList;

public class BestsellerAdapter extends RecyclerView.Adapter<BestsellerAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<BestsellerModel> bestsellerList;

    public BestsellerAdapter(Context context, ArrayList<BestsellerModel> bestsellerList) {
        this.context = context;
        this.bestsellerList = bestsellerList;
    }

    @NonNull
    @Override
    public BestsellerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_bestsellers,parent,false);
        return new BestsellerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestsellerAdapter.MyViewHolder holder, int position) {
        holder.product_name.setText(bestsellerList.get(position).getProductname());
        holder.product_dec.setText(bestsellerList.get(position).getProductdec());
        holder.product_rate.setText(bestsellerList.get(position).getRate());
        Glide.with(context).load(bestsellerList.get(position).getDataImage()).into(holder.productimage);
    }

    @Override
    public int getItemCount() {
        return bestsellerList.size();
    }

    public void searchDataList(ArrayList<BestsellerModel> searchlist){
        bestsellerList = searchlist;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView productimage;
        TextView product_name,product_dec,product_rate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productimage = itemView.findViewById(R.id.productViewImage);
            product_name = itemView.findViewById(R.id.productViewName);
            product_dec = itemView.findViewById(R.id.productViewdec);
            product_rate = itemView.findViewById(R.id.productViewRate);
        }
    }
}
