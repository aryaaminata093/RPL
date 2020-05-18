package com.nyoba.loginregis;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media.MediaSessionManager;

import com.nyoba.loginregis.model.BaseResponse;
import com.nyoba.loginregis.network.config.Config;
import com.nyoba.loginregis.network.interfaces.LoginInterface;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    String pid;
    sessionManager sessionManager;
    String pemail;
    String pnama;
    String ptmptlhr;
    String ptgllhr;
    String palamat;
    String pjk;
    String pgoldar;
    EditText email,password;
    Button btn_login;
    ProgressDialog pd;

    public void pdh_regis(View view) {
        Intent i = new Intent(this, RegistrasiActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new sessionManager(getApplicationContext());

        pd = new ProgressDialog(LoginActivity.this);
        email = (EditText) findViewById(R.id.email_lgn);
        password = (EditText) findViewById(R.id.password_lgn);
        btn_login = (Button) findViewById(R.id.btn_login);

        LoginInterface lgin = Config.getClient().create(LoginInterface.class);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Login...");
                pd.setCancelable(false);
                pd.show();

                String semail= email.getText().toString();
                String spassword = password.getText().toString();

                Call<BaseResponse> login = lgin.login(semail,spassword);
                login.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        Log.d("cek","Response :" + response.body().getEmail());


                        pid = response.body().getId();
                        pemail = response.body().getEmail();
                        pnama = response.body().getNama();
                        ptmptlhr = response.body().getTmptlhr();
                        ptgllhr = response.body().getTgllhr();
                        palamat = response.body().getAlamat();
                        pjk = response.body().getJk();
                        pgoldar = response.body().getGoldar();


                        sessionManager.createSession(pnama,pemail,palamat,ptmptlhr,ptgllhr,pjk,pgoldar,pid);

                        pd.hide();
                        int success = response.body().getSuccess();

                        if ( success == 1) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("pid", pid);
                            String message = pid;
                            startActivity(i);
                        } else if( success == 2) {
                            Toast.makeText(LoginActivity.this, "password salah", Toast.LENGTH_SHORT).show();
                        } else if( success == 404){
                            Toast.makeText(LoginActivity.this, "email belum terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                        Log.d("cek","Fail : Gagal mengirim data");
                        System.out.println("onFailure"+call);
                        t.printStackTrace();
                        pd.hide();

                        Toast.makeText(LoginActivity.this, "Error mengirim!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
