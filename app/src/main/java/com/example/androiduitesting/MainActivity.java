package com.example.androiduitesting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<City> cityDataList;
    private ArrayAdapter<City> cityAdapter;

    private Button addCityButton;
    private Button clearButton;
    private Button confirmButton;
    private EditText nameField;
    private LinearLayout nameEntryRow;
    private ListView cityListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        addCityButton = findViewById(R.id.button_add);
        clearButton = findViewById(R.id.button_clear);
        confirmButton = findViewById(R.id.button_confirm);
        nameField = findViewById(R.id.editText_name);
        nameEntryRow = findViewById(R.id.field_nameEntry);
        cityListView = findViewById(R.id.city_list);

        // Model + Adapter
        cityDataList = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityDataList);
        cityListView.setAdapter(cityAdapter);

        // Show the name entry row when Add is clicked
        addCityButton.setOnClickListener(v -> {
            nameField.setText("");
            nameEntryRow.setVisibility(View.VISIBLE);
            nameField.requestFocus();
        });

        // Confirm: add city to list
        confirmButton.setOnClickListener(v -> {
            String name = nameField.getText().toString().trim();
            if (!name.isEmpty()) {
                cityDataList.add(new City(name));
                cityAdapter.notifyDataSetChanged();
                nameField.setText("");
                nameEntryRow.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Clear list
        clearButton.setOnClickListener(v -> {
            cityDataList.clear();
            cityAdapter.notifyDataSetChanged();
            nameEntryRow.setVisibility(View.INVISIBLE);
        });

        // Tap a city -> open ShowActivity with the city name
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            City selected = cityAdapter.getItem(position);
            if (selected == null) return;
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            intent.putExtra(ShowActivity.EXTRA_CITY_NAME, selected.getCityName());
            startActivity(intent);
        });
    }
}
