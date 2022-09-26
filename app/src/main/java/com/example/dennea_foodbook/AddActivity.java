package com.example.dennea_foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

/*
This is the AddActivity class. This is teh activity that the user is directed to when they
want at add a food or edit a food :)
 */

public class AddActivity extends AppCompatActivity {
    EditText foodNameText, foodDescriptionText, foodAmountText, foodCostText;
    Button submitButton, cancelButton;
    Spinner locationSpinner;
    DatePicker datePicker;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Edit Text Boxes --------------------------------
        foodNameText = findViewById(R.id.foodNameText);
        foodDescriptionText = findViewById(R.id.foodDescriptionText);
        foodAmountText = findViewById(R.id.foodAmountText);
        foodCostText = findViewById(R.id.foodCostText);

        // Location Spinner ------------------------------
        locationSpinner = findViewById(R.id.locationSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        // Expiry Date Picker ---------------------------
        datePicker = findViewById(R.id.datePicker);

        //Button -------------------------------------
        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancelButton);
        editOrAdd();
        //onClickSubmit();
        onClickCancel();
    }
    private void editOrAdd() {
        // handle weather we are editing an existing food or adding a new one
        Intent intent = getIntent();
        String editOrAdd = intent.getStringExtra("editOrAdd");
        if (editOrAdd.equals("edit")){
            String newFoodJson = intent.getStringExtra("editFoodJson");
            Gson gson = new Gson();
            Food newFood = gson.fromJson(newFoodJson, Food.class);
            foodNameText.setText(newFood.getName());
            foodDescriptionText.setText(newFood.getDescription());
            String expDate = newFood.getDate();
            String[] splitString = expDate.split("-");
            datePicker.updateDate(Integer.parseInt(splitString[0]),
                    Integer.parseInt(splitString[1]), Integer.parseInt(splitString[2]));
            int spinnerPosition = adapter.getPosition(newFood.getLocation());
            locationSpinner.setSelection(spinnerPosition);
            foodAmountText.setText(String.valueOf(newFood.getCount()));
            foodCostText.setText(String.valueOf(newFood.getCost()));
        }
        onClickSubmit(editOrAdd);
    }

    private void onClickSubmit(String editOrAdd) {
        // Text and Submit
        submitButton.setOnClickListener(view -> {
            // get inputs from user
            String foodName = foodNameText.getText().toString();
            String foodDescription = foodDescriptionText.getText().toString();
            String foodAmount = foodAmountText.getText().toString();
            String foodCost = foodCostText.getText().toString();
            String foodLocation = locationSpinner.getSelectedItem().toString();
            String day = String.valueOf(datePicker.getDayOfMonth());
            String month = String.valueOf(datePicker.getMonth());
            String year = String.valueOf(datePicker.getYear());
            String stringDate = year + "-" + month + "-" + day;

            // send the data back to the main activity
            if (foodCost.matches("-?\\d+(\\.\\d+)?") && foodAmount.matches("-?\\d+(\\.\\d+)?")){
                Intent switchActivityIntent = new Intent(this, MainActivity.class);
                Food newFood = new Food(foodName,foodDescription,stringDate,foodLocation,Integer.parseInt(foodAmount),Integer.parseInt(foodCost));
                Gson gson = new Gson();
                String myJson = gson.toJson(newFood);
                if (editOrAdd.equals("add")){
                    switchActivityIntent.putExtra("newFood", myJson);
                } else {
                    Intent intent = getIntent();
                    String positionEdit = intent.getStringExtra("positionEdit");
                    switchActivityIntent.putExtra("editFood", myJson);
                    switchActivityIntent.putExtra("positionEdit", positionEdit);
                }
                startActivity(switchActivityIntent);
            }
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
