package com.example.wraphumic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin, buttonRegister;
    private EditText Usernamelogin, Passwordlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        Usernamelogin = findViewById(R.id.Usernamelogin);
        Passwordlogin = findViewById(R.id.Passwordlogin);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                login();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void login()
    {
        String username = Usernamelogin.getText().toString();
        String password = Passwordlogin.getText().toString();

        // Lakukan validasi data jika diperlukan
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }
        // Pindah ke halaman login
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void register()
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}