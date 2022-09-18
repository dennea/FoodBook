package com.example.dennea_foodbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// This is the food class. It stores all of the information for the food that the customer adds to
// the list :))
public class Food {
    private String description;
    private Date date;
    private Location location;
    private int count;
    private int cost;

    public Food(String description, Date date, Location location, int count, int cost){
        this.description = description;
        this.date = date;
        this.location = location;
        this.count = count;
        this.cost = cost;
    }

    private void updateDescription(String newDescription){
        this.description = newDescription;
    }

    private void updateDate(Date newDate){
        this.date = newDate;
    }

    private void updateLocation(Location newLocation){
        this.location = newLocation;
    }

    private void updateCount(int newCount){
        this.count = newCount;
    }

    private void updateCost(int newCost){
        this.cost = newCost;
    }
}
