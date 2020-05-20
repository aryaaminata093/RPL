package com.nyoba.loginregis;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nyoba.loginregis.model.DokterModel;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterCari extends RecyclerView.Adapter<AdapterCari.ViewHolder> {

    Dialog mydialog;

    private ArrayList<DokterModel> dokterModels = new ArrayList<>();
    private Context context;


    public AdapterCari(Context context, ArrayList<DokterModel> dokterModels){
        this.dokterModels=dokterModels;
        this.context=context;
    }

    @NonNull
    @Override
    public AdapterCari.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cari_list_item,parent,false);

        AdapterCari.ViewHolder vHolder = new AdapterCari.ViewHolder(view);
        mydialog = new Dialog(context);
        mydialog.setContentView(R.layout.dialog_doc);

        vHolder.peritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialogname= (TextView) mydialog.findViewById(R.id.dianama);
                TextView dialogspesial= (TextView) mydialog.findViewById(R.id.diaspesial);
                String dialogid = dokterModels.get(vHolder.getAdapterPosition()).getIddoc();
                String idjadwal = dokterModels.get(vHolder.getAdapterPosition()).getIdjadwal();
                Button dialogbutton = (Button) mydialog.findViewById(R.id.buatjanji);

                dialogname.setText(dokterModels.get(vHolder.getAdapterPosition()).getNamadoc());
                dialogspesial.setText(dokterModels.get(vHolder.getAdapterPosition()).getSpesialis());
                mydialog.show();

                dialogbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        String nama = dialogname.getText().toString();
                        String spesial = dialogspesial.getText().toString();
                        Intent intent = new Intent(context,makejanji.class);
                        intent.putExtra("did",dialogid);
                        intent.putExtra("namadokter",nama);
                        intent.putExtra("spesialis",spesial);
                        intent.putExtra("idjadwal",idjadwal);
                        context.startActivity(intent);
                    }
                });
                //Toast.makeText(context,"test click"+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
            }

        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCari.ViewHolder holder, int position) {
        holder.doc_nama.setText(dokterModels.get(position).getNamadoc());
        holder.doc_spesial.setText(dokterModels.get(position).getSpesialis());
        holder.jampraktek.setText(dokterModels.get(position).getJam());
    }

    @Override
    public int getItemCount() {
        return dokterModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView doc_nama,doc_spesial,jampraktek;
        private CardView peritem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            peritem = (CardView) itemView.findViewById(R.id.per_item);
            doc_nama = (TextView) itemView.findViewById(R.id.doc_nama);
            doc_spesial = (TextView) itemView.findViewById(R.id.doc_spesial);
            jampraktek = (TextView) itemView.findViewById(R.id.jampraktek);
        }
    }
}
