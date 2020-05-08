package com.cds.rumahpasta.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cds.rumahpasta.Models.Food;
import com.cds.rumahpasta.R;


import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {

    //lakukan kontruktor pertama kali
    public FoodAdapter(Activity context, ArrayList<Food>foods){
        super(context, 0, foods);
    }
    //getview merupakan method turunan dari array adapter
    public View getView(int position, View convertView, ViewGroup parent){
        //variable convetview akan membaca view yg diisi
        View listItemView = convertView;
        //jika list kosong, akan terisi oleh list item
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        //currentfood mengambil data dari kelas food yg telah dibuat

        Food currentFood = getItem(position);
        TextView foodNameTextView = listItemView.findViewById(R.id.food_name_text_view);
        foodNameTextView.setText(currentFood.getFoodName());
        //karena price itu int, harus di convert dulu
        TextView priceTextView = listItemView.findViewById(R.id.price_text_view);
        priceTextView.setText(Integer.toString(currentFood.getFoodPrice()));
        ImageView imageView = listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentFood.getmImageResource());
        imageView.setVisibility(View.VISIBLE);
        return  listItemView;
    }
}
