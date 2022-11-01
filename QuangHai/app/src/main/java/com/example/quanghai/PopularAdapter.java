package com.example.quanghai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder>{
    ArrayList<FoodDomain> categoryFood;
    private Context mContext;

    public PopularAdapter(Context mContext,ArrayList<FoodDomain> categoryFood ) {
        this.categoryFood = categoryFood;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.view_holder_popular, parent,false);
        ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final FoodDomain foodDomain = categoryFood.get(position);

        holder.title.setText(categoryFood.get(position).getTitle());
        holder.pic.setImageResource(categoryFood.get(position).getPic());
        holder.fee.setText(String.valueOf(categoryFood.get(position).getFee()));

        holder.rcv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickDetail(foodDomain);
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetail.class);
                intent.putExtra("object", categoryFood.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    private void onclickDetail(FoodDomain foodDomain) {
        Intent intent =  new Intent(mContext, ShowDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", foodDomain);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return categoryFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rcv_item;
        TextView title;
        ImageView pic;
        TextView fee;
        Button add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rcv_item = itemView.findViewById(R.id.rcv_item);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            fee = itemView.findViewById(R.id.fee);
            add = itemView.findViewById(R.id.add);
        }
    }
}
