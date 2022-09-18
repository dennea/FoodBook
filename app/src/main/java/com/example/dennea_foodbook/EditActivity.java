package com.example.dennea_foodbook;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    /*
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
*/
}
