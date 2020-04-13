package com.rumahsakit.v1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.masuk);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daptar();
            }
        });
    }

    public void daptar() {
        Intent intent = new Intent(this, mainmenu.class);
        startActivity(intent);
    }

    public void daftar(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}