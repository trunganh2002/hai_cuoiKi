package com.example.quanghai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDetail extends AppCompatActivity {
    TextView Name, fee,des,feetxt;
    ImageView pic;
    Button btnTru, btnCong, btnAdd;
    int numberOrder = 1;
    private FoodDomain object;
    private CartManager managermentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managermentCart = new CartManager(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        FoodDomain foodDomain = (FoodDomain) bundle.get("object");
        Name = findViewById(R.id.Name);
        fee = findViewById(R.id.fee);
        des = findViewById(R.id.des);
        pic = findViewById(R.id.pic);
        feetxt = findViewById(R.id.feetxt);
        btnTru = findViewById(R.id.btnTru);
        btnCong = findViewById(R.id.btnCong);
        btnAdd = findViewById(R.id.btnAdd);

        Name.setText(foodDomain.getTitle());
        fee.setText(String.valueOf(foodDomain.getFee()));
        des.setText(foodDomain.getDescription());
        pic.setImageResource(foodDomain.getPic());
        feetxt.setText(String.valueOf(numberOrder));
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                feetxt.setText(String.valueOf(numberOrder));
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1){
                    numberOrder = numberOrder - 1;
                }
                feetxt.setText(String.valueOf(numberOrder));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodDomain object = (FoodDomain) getIntent().getSerializableExtra("object");
                managermentCart.insertFood(object);
            }
        });


        initView();
    }


    private void initView() {
        Name = findViewById(R.id.Name);
        fee = findViewById(R.id.fee);
        des = findViewById(R.id.des);
        pic = findViewById(R.id.pic);
        btnTru = findViewById(R.id.btnTru);
        btnCong = findViewById(R.id.btnCong);
        btnAdd = findViewById(R.id.btnAdd);
        feetxt = findViewById(R.id.feetxt);
    }
}