package com.example.dennea_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class ViewActivity extends AppCompatActivity {
    private FloatingActionButton deleteButton;
    private FloatingActionButton editButton;
    private Button backButton;
    private TextView foodName;
    private TextView foodDescription;
    private TextView foodDate;
    private TextView foodLocation;
    private TextView foodCount;
    private TextView foodCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Buttons and TextView initialization
        deleteButton = findViewById(R.id.deleteFoodButton);
        editButton = findViewById(R.id.editFoodButton);
        backButton = findViewById(R.id.backToMainButton);
        foodName = findViewById(R.id.nameText);
        foodDescription = findViewById(R.id.descriptionText);
        foodDate = findViewById(R.id.dateText);
        foodLocation = findViewById(R.id.locationText);
        foodCount = findViewById(R.id.countText);
        foodCost = findViewById(R.id.costText);
        getFood();
        onBackClick();
        onDeleteClick();
        onEditClick();
    }

    public void getFood() {
        // add the newly submitted food to the array
        Intent intent = getIntent();
        String newFoodJson = intent.getStringExtra("foodJson");
        if (newFoodJson != null){
            Gson gson = new Gson();
            Food newFood = gson.fromJson(newFoodJson, Food.class);
            foodName.setText(newFood.getName());
            foodDescription.setText(newFood.getDescription());
            String stringDate = "Expiry Date: " + newFood.getDate();
            foodDate.setText(stringDate);
            String stringLocation = "Location: " + newFood.getLocation();
            foodLocation.setText(stringLocation);
            String stringAmount = "Amount: " + String.valueOf(newFood.getCount());
            foodCount.setText(stringAmount);
            String stringCost = "Cost: " + String.valueOf(newFood.getCost());
            foodCost.setText(stringCost);
        }
    }

    public void onBackClick(){
        backButton.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            startActivity(switchActivityIntent);
        });
    }

    public void onDeleteClick(){
        deleteButton.setOnClickListener(view -> {
            Intent intent = getIntent();
            String position = intent.getStringExtra("position");
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            switchActivityIntent.putExtra("position", position);
            startActivity(switchActivityIntent);
        });
    }

    public void onEditClick(){
        editButton.setOnClickListener(view -> {
            Intent intent = getIntent();
            String positionEdit = intent.getStringExtra("position");
            String foodJson = intent.getStringExtra("foodJson");
            Intent switchActivityIntent = new Intent(this, AddActivity.class);
            switchActivityIntent.putExtra("editOrAdd","edit");
            switchActivityIntent.putExtra("editFoodJson",foodJson);
            switchActivityIntent.putExtra("positionEdit", positionEdit);
            startActivity(switchActivityIntent);
        });
    }
}