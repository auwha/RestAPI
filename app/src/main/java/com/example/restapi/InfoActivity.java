package com.example.restapi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restapi.databinding.ActivityInfoBinding;
import com.example.restapi.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    ActivityInfoBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInfoBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(b.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(b.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        var extras = getIntent().getExtras();

        b.name.setText(extras.getString("name"));
        b.email.setText(extras.getString("email"));
        b.phoneNumber.setText(extras.getString("phone"));
        b.address.setText(extras.getString("address"));
        b.birthday.setText(extras.getString("birthday"));
        b.gender.setText(extras.getString("gender"));
        Picasso.get().load(extras.getString("image")).into(b.image);
    }
}