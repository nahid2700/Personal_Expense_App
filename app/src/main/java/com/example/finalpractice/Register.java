package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
Button login, register;
EditText name, phoneno, pin, cpin;
SQLiteDatabase db;
String Name, PhoneNo, Pin, C_Pin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = findViewById(R.id.login);
        name = findViewById(R.id.name);
        phoneno = findViewById(R.id.rphoneno);
        pin = findViewById(R.id.rpin);
        cpin = findViewById(R.id.cpin);
        register = findViewById(R.id.register);

        db = openOrCreateDatabase("Final", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(number TEXT primary key, pin TEXT);");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneNo = phoneno.getText().toString();
                Pin = pin.getText().toString();
                C_Pin = cpin.getText().toString();
                if (PhoneNo != null && Pin != null && C_Pin != null && C_Pin.equals(Pin)) {
                    db.execSQL("INSERT INTO user VALUES ('"+PhoneNo+"', '"+Pin+"') ;");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Registration Done", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Fill all", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log In here", Toast.LENGTH_SHORT).show();
            }
        });
    }
}