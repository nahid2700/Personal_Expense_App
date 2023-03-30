package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DailyExpense extends AppCompatActivity {
Button home, summary, total;
EditText day, brakfast, launch, fare, other;
String days, to; float brk, lan, far, ot, amount;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expense);

        home = findViewById(R.id.home);
        summary = findViewById(R.id.summary);
        day = findViewById(R.id.day);
        brakfast = findViewById(R.id.breakfast);
        launch = findViewById(R.id.launch);
        fare = findViewById(R.id.vf);
        other = findViewById(R.id.other);
        total = findViewById(R.id.total);
        db = openOrCreateDatabase("Final", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS total(day TEXT primary key, net TEXT);");

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                days = day.getText().toString();
                brk = Float.parseFloat(brakfast.getText().toString());
                lan = Float.parseFloat(launch.getText().toString());
                far = Float.parseFloat(fare.getText().toString());
                ot = Float.parseFloat(other.getText().toString());
                amount = brk + lan + far + ot;
                to = String.valueOf(amount);
                Toast.makeText(getApplicationContext(), "Total Cost"+to, Toast.LENGTH_SHORT).show();
                db.execSQL("INSERT INTO total VALUES('"+days+"', '"+to+"');");
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Go back to log in page", Toast.LENGTH_SHORT).show();
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Summary.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Viewing Summary", Toast.LENGTH_SHORT).show();
            }
        });
    }
}