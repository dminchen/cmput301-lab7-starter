package com.example.androiduitesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends Activity {
    public static final String EXTRA_CITY_NAME = "city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView tv = findViewById(R.id.city_name);
        Button back = findViewById(R.id.button_back);

        Intent i = getIntent();
        String city = (i != null) ? i.getStringExtra(EXTRA_CITY_NAME) : "";
        if (city == null) city = "";
        tv.setText(city);

        back.setOnClickListener(v -> finish());
    }
}
