package com.nyoba.loginregis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nyoba.loginregis.model.DokterModel;
import com.nyoba.loginregis.model.ModelJanji;
import com.nyoba.loginregis.network.config.Config;
import com.nyoba.loginregis.network.interfaces.CariInterface;
import com.nyoba.loginregis.network.interfaces.lihatjanji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class janji extends Fragment {
    String pid;
    EditText jcari;
    Button btnsrc;
    sessionManager sessionManager;
    ProgressDialog pd;
    TextView janjihari,namadoc,spesialdoc;

    public static janji newInstance(String param1, String param2) {
        janji fragment = new janji();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new sessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        pid = user.get(sessionManager.ID);
        pd = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View janji = inflater.inflate(R.layout.janji_fragment, container, false);
        jcari = (EditText) janji.findViewById(R.id.carilah);
        btnsrc = (Button) janji.findViewById(R.id.btn_src);

        janjihari = (TextView) janji.findViewById(R.id.doc_harijan);
        namadoc = (TextView) janji.findViewById(R.id.doc_namajan);
        spesialdoc = (TextView) janji.findViewById(R.id.doc_spesialjan);

        search(pid);

        btnsrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cari = jcari.getText().toString();
                Intent i = new Intent(getActivity(), HasilCari.class);
                i.putExtra("ccari", cari);
                String message = cari;
                jcari.setText("");
                startActivity(i);
            }
        });


        return janji;
    }


    private void search(String pidi) {
        pd.setMessage("Mencari..");
        pd.setCancelable(false);
        pd.show();
        lihatjanji cari = Config.getClient().create(lihatjanji.class);

        Call<ModelJanji> call = cari.lihat(pidi);
        call.enqueue(new Callback<ModelJanji>() {
            @Override
            public void onResponse(Call<ModelJanji> call, Response<ModelJanji> response) {
                //Log.d("cek","Response :" + response.body().getHarijanji());
                janjihari.setText(response.body().getHarijanji());
                namadoc.setText(response.body().getNamadokter());
                spesialdoc.setText(response.body().getSpesialis());
                pd.hide();
            }

            @Override
            public void onFailure(Call<ModelJanji> call, Throwable t) {
                Log.d("cek","Response :" + "error");
                pd.hide();
            }

        });
    }
}
