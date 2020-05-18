package com.nyoba.loginregis;

import com.nyoba.loginregis.model.BaseResponse;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    TextView nama;
    sessionManager sessionManager;
    TextView tglah;
    TextView email;
    TextView pidi;


    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View home = inflater.inflate(R.layout.home_fragment, container, false);


        sessionManager = new sessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        String name = user.get(sessionManager.NAME);
        String emaili = user.get(sessionManager.EMAIL);
        String tgl = user.get(sessionManager.TGLTLAHIR);
        String pidai = user.get(sessionManager.ID);

        return home;
    }
}
