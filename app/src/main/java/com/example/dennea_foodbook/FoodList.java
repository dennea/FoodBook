package com.example.dennea_foodbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FoodList {
    private ArrayList<Food> foodList;

    public FoodList(){
        foodList = new ArrayList<>();
    }

    public void addFood(String name, String description, String date, Location location, int count, int cost){
        Date parsedDate = parseDate(date);
        Food food = new Food(name,description,parsedDate,location,count,cost);
        foodList.add(food);
    }

    public void deleteFood(int index){
        foodList.remove(index);
    }

    public ArrayList<Food> getFoodList(){
        return foodList;
    }

    public String getTotalCost(){
        int totalCost = 0;
        for (int i = 0; i < foodList.size();i++ ){
            totalCost += (foodList.get(i).getCost() * foodList.get(i).getCount());
        }
        return String.valueOf(totalCost);
    }

    // parse date method from stackoverflow is I want turn String to date :))
    // note: static means it belongs to the class not the instance of the class
    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
