package com.example.flarzehashstash.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flarzehashstash.R;
import com.example.flarzehashstash.data.Hash_List;
import com.example.flarzehashstash.data.Hash_adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashFragment extends Fragment {

    RecyclerView recyclerView;
    Hash_adapter adapter;
    List<Hash_List> hashLists = new ArrayList<>();
    View view;

    public HashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hash, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.hash_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        hashLists.add(new Hash_List(R.drawable.demoman, "#This is delicious", "5, Feb 2019", "08:29:12"));
        hashLists.add(new Hash_List(R.drawable.demoman, "#This is delicious", "5, Feb 2019", "08:29:12"));
        hashLists.add(new Hash_List(R.drawable.demoman, "#This is delicious", "5, Feb 2019", "08:29:12"));
        hashLists.add(new Hash_List(R.drawable.demoman, "#This is delicious", "5, Feb 2019", "08:29:12"));
        adapter = new Hash_adapter(getContext(), hashLists);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
