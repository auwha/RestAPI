package com.example.restapi.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restapi.InfoActivity;
import com.example.restapi.R;
import com.example.restapi.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentHomeBinding b = FragmentHomeBinding.inflate(inflater, container, false);

        b.btn.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), InfoActivity.class));
        });

        return b.getRoot();
    }
}