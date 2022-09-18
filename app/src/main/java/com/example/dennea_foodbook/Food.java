package com.example.dennea_foodbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// This is the food class. It stores all of the information for the food that the customer adds to
// the list :))
public class Food {
    private String name;
    private String description;
    private Date date;
    private Location location;
    private int count;
    private int cost;

    public Food(String name, String description, Date date, Location location, int count, int cost){
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.count = count;
        this.cost = cost;
    }

    public void updateName(String newName){
        this.name = newName;
    }

    public void updateDescription(String newDescription){
        this.description = newDescription;
    }

    public void updateDate(Date newDate){
        this.date = newDate;
    }

    public void updateLocation(Location newLocation){
        this.location = newLocation;
    }

    public void updateCount(int newCount){
        this.count = newCount;
    }

    public void updateCost(int newCost){
        this.cost = newCost;
    }

    public int getCost(){
        return this.cost;
    }

    public int getCount(){
        return this.count;
    }
}
