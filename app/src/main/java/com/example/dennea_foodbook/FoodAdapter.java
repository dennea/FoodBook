package com.example.dennea_foodbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
/*
This is the FoodAdapter class. Connects the list that displays the food :))
 */
public class FoodAdapter extends ArrayAdapter<Food> {
    private Context context;
    private ArrayList<Food> dataList;

    public FoodAdapter(Context context, ArrayList<Food> dataList){
        super(context,0, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.food_list_item,parent,false);
        }

        TextView nameText = view.findViewById(R.id.foodName_text);
        TextView countText = view.findViewById(R.id.count_text);
        TextView costText = view.findViewById(R.id.cost_text);

        Food currentFood = dataList.get(position);

        nameText.setText(currentFood.getName());
        countText.setText(String.valueOf(currentFood.getCount()));
        costText.setText(String.valueOf(currentFood.getCost()));

        return view;
    }
}
