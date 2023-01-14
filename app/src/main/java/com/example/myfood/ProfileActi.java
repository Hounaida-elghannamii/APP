package com.example.myfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActi extends AppCompatActivity {
    TextView fn, ln, e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fn = findViewById(R.id.fn);
        ln = findViewById(R.id.ln);
        e = findViewById(R.id.e);

        String first_name = getIntent().getStringExtra("first_name");
        String last_name = getIntent().getStringExtra("last_name");
        String email = getIntent().getStringExtra("email");
        fn.setText(first_name);
        ln.setText(last_name);
        e.setText(email);


        buttonNavigation();
    }

    private void buttonNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout supBtn = findViewById(R.id.supBtn);
        LinearLayout setBtn = findViewById(R.id.setBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActi.this, ProfileActi.class);
                intent.putExtra("first_name", fn.getText().toString());
                intent.putExtra("last_name", ln.getText().toString());
                intent.putExtra("email", e.getText().toString());

                startActivity(intent);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActi.this, MainActivity.class);
                intent.putExtra("first_name", fn.getText().toString());
                intent.putExtra("last_name", ln.getText().toString());
                intent.putExtra("email", e.getText().toString());
                startActivity(intent);
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActi.this, CartActivity.class);
                intent.putExtra("first_name", fn.getText().toString());
                intent.putExtra("last_name", ln.getText().toString());
                intent.putExtra("email", e.getText().toString());
                startActivity(intent);
            }
        });
        supBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActi.this, PageActivity.class);
                intent.putExtra("first_name", fn.getText().toString());
                intent.putExtra("last_name", ln.getText().toString());
                intent.putExtra("email", e.getText().toString());
                startActivity(intent);
            }
        });
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActi.this, ParaActivity.class);
                intent.putExtra("first_name", fn.getText().toString());
                intent.putExtra("last_name", ln.getText().toString());
                intent.putExtra("email", e.getText().toString());
                startActivity(intent);
            }
        });
    }
}
