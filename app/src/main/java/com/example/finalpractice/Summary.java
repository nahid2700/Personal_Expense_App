package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends AppCompatActivity {
Button home, prev, next;
TextView day, total;
SQLiteDatabase db;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        home = findViewById(R.id.home);
        day = findViewById(R.id.daydisplay);
        total = findViewById(R.id.expensedisplay);
        prev = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        db = openOrCreateDatabase("Final", MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select * from total", null);
        c.moveToFirst();
        day.setText(c.getString(c.getColumnIndex("day")));
        total.setText(c.getString(c.getColumnIndex("net")));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    c.moveToNext();
                    day.setText(c.getString(c.getColumnIndex("day")));
                    total.setText(c.getString(c.getColumnIndex("net")));
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Last Record", Toast.LENGTH_SHORT).show();
                }

            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    c.moveToPrevious();
                    day.setText(c.getString(c.getColumnIndex("day")));
                    total.setText(c.getString(c.getColumnIndex("net")));
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "First Record", Toast.LENGTH_SHORT).show();
                }

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
    }
}