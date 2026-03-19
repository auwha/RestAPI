package com.example.restapi.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restapi.CallAPI;
import com.example.restapi.Item;
import com.example.restapi.ItemAdapter;
import com.example.restapi.R;
import com.example.restapi.databinding.FragmentItemListBinding;

import java.util.ArrayList;
import java.util.List;

// dodac to jako customowe api https://restcountries.com/

public class ItemListFragment extends Fragment {

    public ItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentItemListBinding b = FragmentItemListBinding.inflate(inflater, container, false);

        ItemAdapter itemAdapter = new ItemAdapter(getContext());
        b.recyclerView.setAdapter(itemAdapter);

        CallAPI callAPI = new CallAPI();
        callAPI.execute(10, items -> {
            itemAdapter.setDataset(items);
            itemAdapter.notifyDataSetChanged();
        });

        return b.getRoot();
    }
}