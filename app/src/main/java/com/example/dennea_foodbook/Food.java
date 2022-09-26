package com.example.dennea_foodbook;

// This is the food class. It stores all of the information for the food that the customer adds to
// the list :))
public class Food {
    private String name;
    private String description;
    private String date;
    private String location;
    private int count;
    private int cost;

    public Food(String name, String description, String date, String location, int count, int cost) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.count = count;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getCount() {
        return count;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }
}