package com.rodpil.smsstat;

/**
 * Created by Tiago on 25/05/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<Views> {

    List<Objects> list;


    public MyAdapter(List<Objects> list) {
        this.list = list;
    }

    @Override
    public Views onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards,viewGroup,false);
        return new Views(view);
    }

    @Override
    public void onBindViewHolder(Views views, int position) {
        Objects objects = list.get(position);
        views.bind(objects);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}