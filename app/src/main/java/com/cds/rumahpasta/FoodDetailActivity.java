package com.cds.rumahpasta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cds.rumahpasta.Database.SQLiteHelper;
import com.cds.rumahpasta.Models.Food;

import org.w3c.dom.Text;

public class FoodDetailActivity extends AppCompatActivity {

    private int item = 0;
    private  int price = 0;
    TextView sumTextView;
    TextView priceTotalTextView;
    TextView txtName, txtQuantity, txtPrice;
    Button btnAdd, btnCart;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail2);

        init();
        sqLiteHelper = new SQLiteHelper(this, "FoodDB.sqlite", null, 1);
        sqLiteHelper.querydata("CREATE TABLE IF NOT EXISTS CART(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, quantity INTEGER , price INTEGER)");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item<1){
                    Toast.makeText(FoodDetailActivity.this, "Maaf Anda tidak dapat memesan kurang dari 1", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    Log.i("TXT PRICE", txtName.getText().toString());
                    sqLiteHelper.insertData(txtName.getText().toString().trim(), txtQuantity.getText().toString().trim(), txtPrice.getText().toString().trim());
                    Toast.makeText(getApplicationContext(), "Masuk Keranjang !", Toast.LENGTH_SHORT).show();
                    txtQuantity.setText("0");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDetailActivity.this, CartListActivity.class);
                startActivity(intent);
            }
        });
        String name = getIntent().getStringExtra("name");
        String typs = getIntent().getStringExtra("type");
        int image;
        image = getIntent().getIntExtra("image", -1);
        price = getIntent().getIntExtra("price", -1);
        Log.e("SECOND ACTIVITY", name);


        TextView nameTextView = findViewById(R.id.food_name_text_view);
        nameTextView.setText(name);
        TextView type = findViewById(R.id.type);
        type.setText(typs);
        ImageView imageView = findViewById(R.id.food_image);
        imageView.setImageResource(image);
        imageView.setVisibility(View.VISIBLE);
        TextView priceTextView = findViewById(R.id.price_detail_text_view);
        priceTextView.setText(Integer.toString(price));

        sumTextView = findViewById(R.id.sum_text_view);
        priceTotalTextView = findViewById(R.id.price_total_text_view);
        Button incrementButon = findViewById(R.id.increment_button);
        incrementButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });
        Button decrementButton = findViewById(R.id.decrement_button);
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }
        });
    }
    private  void  init(){
        txtName = findViewById(R.id.food_name_text_view);
        txtQuantity = findViewById(R.id.sum_text_view);
        txtPrice = findViewById(R.id.price_total_text_view);
        btnAdd = findViewById(R.id.order_button);
        btnCart = findViewById(R.id.cart_button);
    }
    private void increment(){
        item++;
        sumTextView.setText(Integer.toString(item));

        priceTotalTextView.setText(Integer.toString(sumOfProduct(price)));
    }
    private void decrement(){
        if (item < 1 ){
            Toast.makeText(this, "Maaf Anda tidak dapat memesan kurang dari 1", Toast.LENGTH_SHORT).show();
            return;
        }
        item = item -1;
        sumTextView.setText(Integer.toString(item));

        priceTotalTextView.setText(Integer.toString(sumOfProduct(price)));
    }
    private int sumOfProduct(int price){
        return item * price;
    }
}
