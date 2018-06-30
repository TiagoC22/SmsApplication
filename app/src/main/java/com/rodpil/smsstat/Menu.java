package com.rodpil.smsstat;

/**
 * Created by Tiago on 25/05/2018.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Menu extends Fragment {
    private RecyclerView recyclerView;
    private List<Objects> cities = new ArrayList<>();

    public Menu() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        addContact();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(cities));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void addContact() {

    }
}