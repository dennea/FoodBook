package com.example.dennea_foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class AddActivity extends AppCompatActivity {
    EditText foodNameText, foodDescriptionText, foodDateText, foodAmountText, foodCostText;
    Button submitButton, cancelButton;
    Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Edit Text Boxes --------------------------------
        foodNameText = findViewById(R.id.foodNameText);
        foodDescriptionText = findViewById(R.id.foodDescriptionText);
        foodDateText = findViewById(R.id.foodDateText);
        foodAmountText = findViewById(R.id.foodAmountText);
        foodCostText = findViewById(R.id.foodCostText);

        // Location Spinner ------------------------------
        locationSpinner = findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        //Button -------------------------------------
        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancelButton);
        onClickSubmit();
        onClickCancel();
    }

    private void onClickSubmit(){
        // Text and Submit
        submitButton.setOnClickListener(view -> {
            // get inputs from user
            String foodName = foodNameText.getText().toString();
            String foodDescription = foodDescriptionText.getText().toString();
            String foodDate = foodDateText.getText().toString();
            String foodAmount = foodAmountText.getText().toString();
            String foodCost = foodCostText.getText().toString();
            String foodLocation = locationSpinner.getSelectedItem().toString();

            // pass data from one activity to another:
            // https://codingwitht.com/how-to-pass-data-from-one-activity-to-another-in-android-studio/
            // send the data back to the main activity

            Food newFood = new Food(foodName,foodDescription,foodDate,foodLocation,Integer.parseInt(foodAmount),Integer.parseInt(foodCost));
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            Gson gson = new Gson();
            String myJson = gson.toJson(newFood);
            switchActivityIntent.putExtra("newFood", myJson);
            startActivity(switchActivityIntent);
        });
    }

    private void onClickCancel(){
        // Go back to main without submitting
        cancelButton.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            startActivity(switchActivityIntent);
        });
    }
}
