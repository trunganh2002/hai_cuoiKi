package com.example.quanghai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryDoman> categoryDoman;
    private Context mContext;

    public CategoryAdapter(ArrayList<CategoryDoman> categoryDoman, Context mContext) {
        this.categoryDoman = categoryDoman;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.viewholder_category, parent,false);
        ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDoman.get(position).getTitle());
        holder.categoryPic.setImageResource(categoryDoman.get(position).getPic());
    }


    @Override
    public int getItemCount() {
        return categoryDoman.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
        }


    }
}
