package com.rodpil.smsstat;

/**
 * Created by Tiago on 25/05/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Views extends RecyclerView.ViewHolder{

    private TextView Nom;
    private TextView Tel;
    private TextView compteur;

    public Views(View itemView) {
        super(itemView);


        Nom = (TextView) itemView.findViewById(R.id.cell_name);
        Tel = (TextView) itemView.findViewById(R.id.cell_phone);
        compteur = (TextView) itemView.findViewById(R.id.cell_counter);
    }

    public void bind(Objects objects){
        Nom.setText(objects.getName());
        Tel.setText(objects.getPhone());
        compteur.setText(objects.getCounter());
    }
}