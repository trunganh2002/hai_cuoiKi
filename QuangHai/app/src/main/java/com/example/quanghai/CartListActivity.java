package com.example.quanghai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private CartManager managementCart;
    TextView totalFeeTxt, deliveryTxt, totalTxt,emptyTxt,taxTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new CartManager(this);

        initView();
        initList();
        Caculator();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.taxTxt);
        emptyTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliverrytxt);
        totalTxt = findViewById(R.id.totalTxt);
        scrollView = findViewById(R.id.scrollView);
        taxTxt = findViewById(R.id.taxTxt);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter= new cartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                Caculator();
            }
        });

        recyclerViewList.setAdapter(adapter);



    }
    private void Caculator(){
        double percentTax = 0.2;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() + percentTax) * 100) /100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }

}