package com.example.wraphumic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText rnama, rusername, rpassword;
    public String nama, username, password;
    Button regbtn;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rnama = findViewById(R.id.Register_Nama);
        rusername = findViewById(R.id.Register_Username);
        rpassword = findViewById(R.id.Register_Password);
        regbtn = findViewById(R.id.Reg_Button);
        back = findViewById(R.id.back);

        regbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                DatabaseUser mydb = new DatabaseUser(Register.this);
                mydb.TambahData(rnama.getText().toString(),
                        rusername.getText().toString().trim(),
                        rpassword.getText().toString().trim());
                Toast.makeText(getBaseContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }
    public void back()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}