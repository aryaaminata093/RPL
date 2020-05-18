package com.nyoba.loginregis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nyoba.loginregis.model.BaseResponse;
import com.nyoba.loginregis.model.ModelJanji;
import com.nyoba.loginregis.network.config.Config;
import com.nyoba.loginregis.network.interfaces.JanjiInterface;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class makejanji extends AppCompatActivity {

    sessionManager sessionManager;
    String did,dname,dspesi;
    String pasid,harijanji;
    TextView namadoc, spesialdoc;
    EditText hari;
    Button btnmakejanji;
    ProgressDialog pd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makejanji);

        pd = new ProgressDialog(this);
        sessionManager = new sessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        pasid = user.get(sessionManager.ID);

        Bundle extras = getIntent().getExtras();
        did = extras.getString("did");
        dname = extras.getString("namadokter");
        dspesi = extras.getString("spesialis");
        Log.d("cek in make janji","Response :" + did+" "+pasid);

        namadoc = (TextView) findViewById(R.id.mdocnama);
        spesialdoc = (TextView) findViewById(R.id.mdocspesial);

        btnmakejanji = (Button) findViewById(R.id.btn_makejanji);
        final Spinner hari = findViewById(R.id.listItem);

        namadoc.setText(dname);
        spesialdoc.setText(dspesi);

        JanjiInterface janji = Config.getClient().create(JanjiInterface.class);

        btnmakejanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                harijanji = hari.getSelectedItem().toString();
                pd.setMessage("Sending Data...");
                pd.setCancelable(false);
                pd.show();

                Call<ModelJanji> set = janji.buatjanji(pasid,did,harijanji);
                set.enqueue(new Callback<ModelJanji>() {
                    @Override
                    public void onResponse(Call<ModelJanji> call, Response<ModelJanji> response) {
                        Log.d("cek","Response :" + response.body().getSuccess());

                        pd.hide();
                        int success = response.body().getSuccess();

                        if ( success == 1 ){
                            Toast.makeText(makejanji.this, "Berhasil membuat janji!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(makejanji.this, MainActivity.class);
                            startActivity(i);
                        } else if(success == 2) {
                            Toast.makeText(makejanji.this, "Anda sudah ada janji", Toast.LENGTH_SHORT).show();
                        } else if(success == 3) {
                            Toast.makeText(makejanji.this, "Dokter sudah ada janji", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(makejanji.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelJanji> call, Throwable t) {
                        Log.d("cek","Fail : Gagal mengirim data");
                        System.out.println("onFailure"+call);
                        t.printStackTrace();
                        pd.hide();

                        Toast.makeText(makejanji.this, "Error mengirim!", Toast.LENGTH_SHORT).show();
                    }
                });


                //Toast.makeText(makejanji.this,"data "+did+" "+pasid+" "+harijanji,Toast.LENGTH_SHORT).show();
            }
        });

    }

}
