package com.rahul.jhakadi.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahul.jhakadi.R;
public class CartFragment extends Fragment {
    public CartFragment() {
        // Required empty public constructor
    }
    public static CartFragment newInstance() {
        return new CartFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}