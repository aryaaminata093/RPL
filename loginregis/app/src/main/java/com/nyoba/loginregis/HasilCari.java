package com.nyoba.loginregis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nyoba.loginregis.model.DokterModel;
import com.nyoba.loginregis.network.config.Config;
import com.nyoba.loginregis.network.interfaces.CariInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilCari extends AppCompatActivity {
    String cari;
    EditText caricari;
    Button btnsrc;

    ArrayList<DokterModel> dokterModels = new ArrayList<>();
    private  AdapterCari adaptercari;
    private RecyclerView cari_recyclerView;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_cari);

        pd = new ProgressDialog(HasilCari.this);

        Bundle extras = getIntent().getExtras();
        cari = extras.getString("ccari");
        caricari = (EditText) findViewById(R.id.caricari);
        caricari.setText(cari);
        Log.d("cek in main","Response :" + cari);

        cari_recyclerView = (RecyclerView) findViewById(R.id.cari_recyclerview);
        cari_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search(caricari);

        btnsrc = (Button) findViewById(R.id.btn_src);
        btnsrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Mencari..");
                pd.setCancelable(false);
                pd.show();
                CariInterface cari = Config.getClient().create(CariInterface.class);
                String carilagi = caricari.getText().toString();

                Call<List<DokterModel>> call = cari.getJson(carilagi);
                call.enqueue(new Callback<List<DokterModel>>() {
                    @Override
                    public void onResponse(Call<List<DokterModel>> call, Response<List<DokterModel>> response) {
                        dokterModels=new ArrayList<>(response.body());
                        adaptercari=new AdapterCari(HasilCari.this,dokterModels);
                        cari_recyclerView.setAdapter(adaptercari);
                        if(dokterModels.size() < 1){
                            Toast.makeText(HasilCari.this,"Hasil Tidak ditemukan",Toast.LENGTH_SHORT).show();
                        }
                        pd.hide();
                    }

                    @Override
                    public void onFailure(Call<List<DokterModel>> call, Throwable t) {
                        Toast.makeText(HasilCari.this,"Gagal",Toast.LENGTH_SHORT).show();
                        pd.hide();

                    }
                });
            }
        });

    }

    private void search(EditText caricari) {
        pd.setMessage("Mencari..");
        pd.setCancelable(false);
        pd.show();
        CariInterface cari = Config.getClient().create(CariInterface.class);
        String caris = caricari.getText().toString();

        Call<List<DokterModel>> call = cari.getJson(caris);
        call.enqueue(new Callback<List<DokterModel>>() {
            @Override
            public void onResponse(Call<List<DokterModel>> call, Response<List<DokterModel>> response) {
                dokterModels=new ArrayList<>(response.body());
                adaptercari=new AdapterCari(HasilCari.this,dokterModels);
                cari_recyclerView.setAdapter(adaptercari);
                if(dokterModels.size() < 1){
                    Toast.makeText(HasilCari.this,"Hasil Tidak di temukan",Toast.LENGTH_SHORT).show();
                }
                pd.hide();
            }

            @Override
            public void onFailure(Call<List<DokterModel>> call, Throwable t) {
                Toast.makeText(HasilCari.this,"Gagal",Toast.LENGTH_SHORT).show();
                pd.hide();

            }
        });
    }

}
