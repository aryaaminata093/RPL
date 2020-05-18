package com.nyoba.loginregis;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nyoba.loginregis.model.DokterModel;
import com.nyoba.loginregis.model.ModelJanji;

import java.util.ArrayList;

public class adapterjanji extends RecyclerView.Adapter<adapterjanji.ViewHolder> {


    private ArrayList<ModelJanji> Models = new ArrayList<>();
    private Context context;


    public adapterjanji(Context context, ArrayList<ModelJanji> Models){
        this.Models=Models;
        this.context=context;
    }

    @NonNull
    @Override
    public adapterjanji.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.janji_list_item,parent,false);

        adapterjanji.ViewHolder vHolder = new adapterjanji.ViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterjanji.ViewHolder holder, int position) {
        holder.doc_nama.setText(Models.get(position).getNamadokter());
        holder.doc_spesial.setText(Models.get(position).getSpesialis());
        holder.hari.setText(Models.get(position).getHarijanji());
    }

    @Override
    public int getItemCount() {
        return Models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView doc_nama,doc_spesial,hari;
        private CardView peritem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            peritem = (CardView) itemView.findViewById(R.id.janji_list);
            doc_nama = (TextView) itemView.findViewById(R.id.doc_namajan);
            doc_spesial = (TextView) itemView.findViewById(R.id.doc_spesialjan);
            hari = (TextView) itemView.findViewById(R.id.doc_harijan);
        }
    }
}
