package com.rahul.jhakadi.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rahul.jhakadi.Fragment.CartFragment;
import com.rahul.jhakadi.Fragment.HomeFragment;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> arrayList;
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity,ArrayList<Fragment> arrayList) {
        super(fragmentActivity);
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
