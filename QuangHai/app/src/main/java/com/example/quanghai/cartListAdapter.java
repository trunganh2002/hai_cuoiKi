package com.example.quanghai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cartListAdapter extends RecyclerView.Adapter<cartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private CartManager managementCart;
    private ChangeNumberItemListener changeNumberItemListener;

    public cartListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new CartManager(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round(foodDomains.get(position).getNumberIncart() * foodDomains.get(position).getFee()*100 )/100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberIncart()));
        holder.pic.setImageResource(foodDomains.get(position).getPic());

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNUmberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem,num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusBtn);
            minusItem = itemView.findViewById(R.id.minusBTn);
            totalEachItem = itemView.findViewById(R.id.totalEach);
            num = itemView.findViewById(R.id.numberItemtxt);
        }
    }
}
