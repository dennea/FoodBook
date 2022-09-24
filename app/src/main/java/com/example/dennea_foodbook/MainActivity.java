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
    private static ArrayList<Food> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for testing purposes only
        String []food = {"Apple", "Orange", "Cereal"};
        String []description = {"4","5","4"};
        String []date = {"4","5","4"};
        String []location = {"4","5","4"};
        String []count = {"1","5","2"};
        String []cost = {"4","5","4"};

        foodList = findViewById(R.id.foodListViewId);

        for (int i = 0; i < food.length; i++){
            //dataList.add(new Food(food[i],description[i],date[i],location[i],Integer.parseInt(count[i]),Integer.parseInt(cost[i])));
        }
        foodAdapter = new FoodAdapter(this, dataList);
        foodList.setAdapter(foodAdapter);
        addFoodButton = findViewById(R.id.addButton);
        onClickAddFood();
        getNewFood();
        onClickFood();
        deleteFood();
        updateFood();
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
}