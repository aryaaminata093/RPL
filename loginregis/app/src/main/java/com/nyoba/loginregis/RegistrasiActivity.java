package com.nyoba.loginregis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nyoba.loginregis.model.BaseResponse;
import com.nyoba.loginregis.network.config.Config;
import com.nyoba.loginregis.network.interfaces.RegistrasiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {

    EditText nama,email,password;
    Button btn_regis;
    ProgressDialog pd;

    public void pdh_login(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);


        pd = new ProgressDialog(this);
        nama = (EditText) findViewById(R.id.nama);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btn_regis = (Button) findViewById(R.id.btn_regist);

        RegistrasiInterface api = Config.getClient().create(RegistrasiInterface.class);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Sending Data...");
                pd.setCancelable(false);
                pd.show();

                String snama = nama.getText().toString();
                String semail= email.getText().toString();
                String spassword = password.getText().toString();

                Call<BaseResponse> senddata = api.registrasi(semail, snama, spassword);
                senddata.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    Log.d("cek","Response :" + response.body().getSuccess());

                        pd.hide();
                        int success = response.body().getSuccess();

                        if ( success == 1 ){
                            Toast.makeText(RegistrasiActivity.this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrasiActivity.this, "Registrasi Gagal!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.d("cek","Fail : Gagal mengirim data");
                        System.out.println("onFailure"+call);
                        t.printStackTrace();
                        pd.hide();

                        Toast.makeText(RegistrasiActivity.this, "Error mengirim!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
