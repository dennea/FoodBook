package com.example.dennea_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

// This is the Main Activity that handles the main screen of the app

public class MainActivity extends AppCompatActivity {
    private ListView foodList;
    private Button addFoodButton;
    private FoodAdapter foodAdapter;
    private TextView costText;
    private static ArrayList<Food> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodList = findViewById(R.id.foodListViewId);
        costText = findViewById(R.id.totalCostText);

        foodAdapter = new FoodAdapter(this, dataList);
        foodList.setAdapter(foodAdapter);
        addFoodButton = findViewById(R.id.addButton);
        onClickAddFood();
        getNewFood();
        onClickFood();
        deleteFood();
        updateFood();
        displayTotalCost();
    }

    public void getNewFood() {
        // add the newly submitted food to the array
        Intent intent = getIntent();
        String newFoodJson = intent.getStringExtra("newFood");
        if (newFoodJson != null){
            Gson gson = new Gson();
            Food newFood = gson.fromJson(newFoodJson, Food.class);
            dataList.add(newFood);
        }
    }

    public void onClickAddFood() {
        // go to add food activity to put in new info
        addFoodButton.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, AddActivity.class);
            switchActivityIntent.putExtra("editOrAdd","add");
            startActivity(switchActivityIntent);
        });
    }

    public void onClickFood() {
        // when a food is pressed go to the view activity
        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Gson gson = new Gson();
                String foodJson = gson.toJson(dataList.get(i));
                Intent switchActivityIntent = new Intent(MainActivity.this, ViewActivity.class);
                switchActivityIntent.putExtra("foodJson", foodJson);
                switchActivityIntent.putExtra("position", String.valueOf(i));
                startActivity(switchActivityIntent);
            }
        });
    }

    public void deleteFood() {
        // delete a food from the list
        Intent intent = getIntent();
        String position = intent.getStringExtra("position");
        if (position != null) {
            dataList.remove(Integer.parseInt(position));
        }
    }

    public void updateFood() {
        // we edited a food, now update it in the list
        Intent intent = getIntent();
        String editFood = intent.getStringExtra("editFood");
        if (editFood != null) {
            Gson gson = new Gson();
            Food newEditFood = gson.fromJson(editFood, Food.class);
            String positionEdit = intent.getStringExtra("positionEdit");
            dataList.set(Integer.parseInt(positionEdit),newEditFood);
        }

    }

    public void displayTotalCost(){
        // calculate and display the cost :))
        int cost = 0;
        for (int i= 0; i< dataList.size(); i++) {
            cost += (dataList.get(i).getCost() * dataList.get(i).getCount());
        }
        String costTextString = "Total Cost: $" + String.valueOf(cost);
        costText.setText(costTextString);

    }
}