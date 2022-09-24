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

// myTextBox.setText("My Product Description");
// switch between activities: https://learntodroid.com/how-to-switch-between-activities-in-android/
// https://developer.android.com/develop/ui/views/components/dialogs

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
        addFoodButton.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, AddActivity.class);
            switchActivityIntent.putExtra("editOrAdd","add");
            startActivity(switchActivityIntent);
        });
    }

    public void onClickFood() {
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
        Intent intent = getIntent();
        String position = intent.getStringExtra("position");
        if (position != null) {
            dataList.remove(Integer.parseInt(position));
        }
    }

    public void updateFood() {
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
        int cost = 0;
        for (int i= 0; i< dataList.size(); i++) {
            cost += (dataList.get(i).getCost() * dataList.get(i).getCount());
        }
        String costTextString = "Total Cost: $" + String.valueOf(cost);
        costText.setText(costTextString);

    }
}