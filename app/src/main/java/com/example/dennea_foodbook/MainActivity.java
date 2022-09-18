package com.example.dennea_foodbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView foodListView;
    //Location testLocation = Location.FRIDGE;
    //Food testfood = new Food("description","2022-05-24",testLocation,3, 23);
    String [] food = {};
    ArrayAdapter<String> foodAdapter;
    ArrayList<String> foodList;
    Button submitButton;
    Button addFoodButton;
    Button delete;
    EditText cityText;
    LinearLayout addFoodLayout;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Layouts
        // the main screen
        mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setVisibility(View.VISIBLE);
        // add a food screen. Not visible at until add button is clicked
        addFoodLayout = findViewById(R.id.addLayout);
        addFoodLayout.setVisibility(View.GONE);

        // Buttons
        // Button to add food
        addFoodButton = findViewById(R.id.addButton);
        // Button to submit new food entry
        submitButton = findViewById(R.id.submitButton);


        foodListView = findViewById(R.id.cityListViewId);
        cityText = findViewById(R.id.foodNameText);
       // delete = findViewById(R.id.deleteButton);

        foodList = new ArrayList<>();
        foodList.addAll(Arrays.asList(food));
        foodAdapter = new ArrayAdapter<>(this,R.layout.food_list_item, foodList);
        foodListView.setAdapter(foodAdapter);

        //Delete Item
        delete();

        //Add food button
        addFood();
    }

    public void delete(){
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                delete.setOnClickListener(view2 -> {
                    foodList.remove(position);
                    foodAdapter.notifyDataSetChanged();
                });
            }
        });
    }

    public void addFood(){
        addFoodButton.setOnClickListener(view -> {
            addFoodLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.GONE);
        });

        // Text and Submit
        submitButton.setOnClickListener(view -> {
            String city = cityText.getText().toString();
            foodList.add(city);
            foodAdapter.notifyDataSetChanged();
            addFoodLayout.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);

        });
    }

    // parse date method from stackoverflow is I want turn String to date :))
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}