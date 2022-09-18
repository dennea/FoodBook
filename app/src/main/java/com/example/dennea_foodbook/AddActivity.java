package com.example.dennea_foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class AddActivity extends AppCompatActivity {
    EditText foodNameText, foodDescriptionText, foodDateText, foodAmountText, foodCostText;
    Button submitButton, cancelButton;

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

            // pass data from one activity to another:
            // https://codingwitht.com/how-to-pass-data-from-one-activity-to-another-in-android-studio/
            // send the data back to the main activity
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            switchActivityIntent.putExtra("foodName", foodName);
            switchActivityIntent.putExtra("foodDescription", foodDescription);
            switchActivityIntent.putExtra("foodDate", foodDate);
            switchActivityIntent.putExtra("foodAmount", foodAmount);
            switchActivityIntent.putExtra("foodCost", foodCost);
            startActivity(switchActivityIntent);
        });
    }

    private void onClickCancel(){
        cancelButton.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            startActivity(switchActivityIntent);
        });
    }
}
