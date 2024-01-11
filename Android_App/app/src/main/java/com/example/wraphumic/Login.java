package com.example.wraphumic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private Button pemeriksaan, logout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pemeriksaan = (Button) findViewById(R.id.pemeriksaan);
        logout = (Button) findViewById(R.id.logout);

        pemeriksaan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                klasifikasi();
            }
        });
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                setLogout();
            }
        });
    }
    public void klasifikasi()
    {
        Intent intent = new Intent(this, klasifikasi_gambar.class);
        startActivity(intent);
    }
    public void setLogout()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}