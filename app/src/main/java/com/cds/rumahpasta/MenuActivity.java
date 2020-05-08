package com.cds.rumahpasta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cds.rumahpasta.Adapter.FoodAdapter;
import com.cds.rumahpasta.Models.Food;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    //untuk nampilin gambar
    public static final int REQUEST_CODE = 1;
    //nilai variabel akan diambil dari FoodAdapter
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("Daftar Menu");

        //Arraylist buatnampung nilai kelompok dan membuat nilai array dari model food
        final ArrayList<Food> foods = new ArrayList<>();
        //Berikut nilai yg dibuat utk mengisi konstruktor
        foods.add(new Food("MINUTEVAN PASTA", R.drawable.a1, 54800, "Spanyol Pasta Food"));
        foods.add(new Food("CAPRESE PASTA SALAD", R.drawable.a2, 48700, "Indonesian Pasta Food"));
        foods.add(new Food("CREAMY AVOCADO PASTA", R.drawable.a3, 56700, "Italian Pasta Food"));
        foods.add(new Food("INSTANT POT CREAMY GARLIC", R.drawable.a4, 48900, "American Pasta Food"));
        foods.add(new Food("ANTIPASTO TORTELLINI PASTA", R.drawable.a5, 54800, "Asian Pasta Food"));
        foods.add(new Food("SALAD PLATTER FOODIECRUSH",  R.drawable.a6, 44800, "Korean Pasta Food"));
        //bertujuan utk menghubungkan data dengan view
        adapter = new FoodAdapter(this, foods);
        ListView orderListView = findViewById(R.id.order_list_view);
        orderListView.setAdapter(adapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MenuActivity.this, FoodDetailActivity.class);

                Food currentFood = foods.get(position);
                Log.e("FooodName", currentFood.getFoodName());
                i.putExtra("name", currentFood.getFoodName());
                i.putExtra("image", currentFood.getmImageResource());
                i.putExtra("price", currentFood.getFoodPrice());
                i.putExtra("type", currentFood.getType());
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent i = new Intent(MenuActivity.this, CartListActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
