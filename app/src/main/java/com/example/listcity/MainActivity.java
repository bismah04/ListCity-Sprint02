package com.example.listcity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;
    private int selectedPosition = -1; // -1 means nothing is selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView cityList = findViewById(R.id.city_list);
        final Button addCityButton = findViewById(R.id.add_city_button);
        final Button deleteCityButton = findViewById(R.id.delete_city_button);
        final Button confirmButton = findViewById(R.id.confirm_button);
        final EditText cityNameEditText = findViewById(R.id.add_city_editText);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);

        // Add City
        addCityButton.setOnClickListener(v -> cityNameEditText.requestFocus());

        // Confirm
        confirmButton.setOnClickListener(v -> {
            String cityName = cityNameEditText.getText().toString();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityNameEditText.setText(""); // Clear field after adding
            }
        });

        // Selection
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position; // this is to save the city that was chosen
        });

        // Delete City
        deleteCityButton.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1; // Reset selection
            }
        });
    }
}
