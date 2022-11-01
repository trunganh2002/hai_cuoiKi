package com.example.quanghai;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CartManager {
    private Context context;
    private TinyDB tinyDB;

    public CartManager(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++){
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready){
            listFood.get(n).setNumberIncart(item.getNumberIncart());
        }else {
            listFood.add(item);
        }
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
    public void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemListener changeNumberItemListener){
        listFood.get(position).setNumberIncart(listFood.get(position).getNumberIncart() + 1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();
    }
    public void minusNUmberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemListener changeNumberItemListener){
        if (listfood.get(position).getNumberIncart() == 1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberIncart(listfood.get(position).getNumberIncart() - 1);
        }

        tinyDB.putListObject("CartList", listfood);
        changeNumberItemListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomain> listfood = getListCart();
        double fee = 0;
        for (int i=0;i<listfood.size();i++){
            fee = fee + (listfood.get(i).getFee()*listfood.get(i).getNumberIncart());
        }
        return fee;
    }



















}
