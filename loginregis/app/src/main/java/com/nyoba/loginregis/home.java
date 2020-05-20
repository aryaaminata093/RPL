package com.nyoba.loginregis;

import com.nyoba.loginregis.model.HomeModel;
import com.nyoba.loginregis.model.ModelJanji;
import com.nyoba.loginregis.network.config.Config;
import com.nyoba.loginregis.network.interfaces.antrianskrng;
import com.nyoba.loginregis.network.interfaces.lihatjanji;
import com.nyoba.loginregis.network.interfaces.noantrian;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    TextView antriansaatini,nomerantrian;
    sessionManager sessionManager;
    ProgressDialog pd;
    String pid;
    String idjadwal;


    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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

        View home = inflater.inflate(R.layout.home_fragment, container, false);


        antriansaatini = (TextView) home.findViewById(R.id.saatiniantri);
        nomerantrian = (TextView) home.findViewById(R.id.noantrian);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);

        liatnoantrian(pid,formattedDate);

        return home;
    }

    private void liatnoantrian(String pid, String formattedDate) {
        pd.setMessage("Wait..");
        pd.setCancelable(false);
        pd.show();
        noantrian cari = Config.getClient().create(noantrian.class);

        Call<HomeModel> call = cari.lihatno(pid,formattedDate);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                nomerantrian.setText(response.body().getNoantrian());
                idjadwal = response.body().getJadwalid();
                Log.d("cek","Response :" + idjadwal);
                antrianskrng(idjadwal,formattedDate);
                pd.hide();
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Log.d("cek","Response :" + "error di sini");
                pd.hide();
            }

        });
    }

    private void antrianskrng(String idjadwal, String formattedDate) {
        antrianskrng cari = Config.getClient().create(antrianskrng.class);

        Call<HomeModel> call = cari.lihatno(idjadwal,formattedDate);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                Log.d("cek","Response :" + response.body());
                antriansaatini.setText(response.body().getNoantrian());
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Log.d("cek","Response :" + "error");
                pd.hide();
            }

        });
    }
}
