package com.example.dennea_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

// myTextBox.setText("My Product Description");
// switch between activities: https://learntodroid.com/how-to-switch-between-activities-in-android/
// https://developer.android.com/develop/ui/views/components/dialogs

public class MainActivity extends AppCompatActivity {
    FoodList storeFood;
    ListView foodListView;
    TextView totalCostText;
    ArrayAdapter<String> foodAdapter;
    ArrayList<String> foodList;
    Button addFoodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Food list and adapter -------------------
        storeFood = new FoodList();
        foodListView = findViewById(R.id.cityListViewId);
        foodList = new ArrayList<>();
        foodAdapter = new ArrayAdapter<>(this,R.layout.food_list_item, foodList);
        foodListView.setAdapter(foodAdapter);

        //TextView --------------------------------
        totalCostText = findViewById(R.id.totalCostText);
        updateTotalCost();

        // Buttons --------------------------------
        addFoodButton = findViewById(R.id.addButton);
        onClickAddFood();
    }

    public void onClickAddFood(){
        addFoodButton.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, AddActivity.class);
            startActivity(switchActivityIntent);
        });
    }

    private void updateTotalCost(){
        String costText = "Total Cost:" + storeFood.getTotalCost();
        totalCostText.setText(costText);
    }
}