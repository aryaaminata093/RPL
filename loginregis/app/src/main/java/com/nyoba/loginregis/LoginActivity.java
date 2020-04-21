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
import com.nyoba.loginregis.network.interfaces.LoginInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button btn_login;
    ProgressDialog pd;

    public void pdh_regis(View view) {
        Intent i = new Intent(this, RegistrasiActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(LoginActivity.this);
        email = (EditText) findViewById(R.id.email_lgn);
        password = (EditText) findViewById(R.id.password_lgn);
        btn_login = (Button) findViewById(R.id.btn_login);

        LoginInterface lgin = Config.getClient().create(LoginInterface.class);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Sending Data...");
                pd.setCancelable(false);
                pd.show();

                String semail= email.getText().toString();
                String spassword = password.getText().toString();

                Call<BaseResponse> login = lgin.login(semail,spassword);
                login.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        Log.d("cek","Response :" + response.body().getSuccess());

                        pd.hide();
                        int success = response.body().getSuccess();

                        if ( success == 1) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
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
