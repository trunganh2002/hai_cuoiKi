package com.example.quanghai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopular;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDoman> category = new ArrayList<>();
        category.add(new CategoryDoman("pizza", R.drawable.cat_1));
        category.add(new CategoryDoman("Burger", R.drawable.cat_2));
        category.add(new CategoryDoman("HotDog", R.drawable.cat_3));
        category.add(new CategoryDoman("Drink", R.drawable.cat_4));
        category.add(new CategoryDoman("Donut", R.drawable.cat_5));

        adapter = new CategoryAdapter(category,this);
        recyclerViewCategoryList.setAdapter(adapter);

    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.recyclerViewPopular);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain>  foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pizza personal",R.drawable.pizza,"Đặc biệt",3.23,1));
        foodList.add(new FoodDomain("Hambuger",R.drawable.logo,"Đặc biệt",4.65,2));
        foodList.add(new FoodDomain("Pizza ultimate",R.drawable.pop_1,"Đặc biệt",1.65,3));
        foodList.add(new FoodDomain("Hambuger",R.drawable.pop_2,"Đặc biệt",4.23,4));
        foodList.add(new FoodDomain("Pizza",R.drawable.pop_3,"Đặc biệt",5.26,5));

        adapter2 = new PopularAdapter(this,foodList);
        recyclerViewCategoryList.setAdapter(adapter2);
    }
}