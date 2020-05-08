package com.cds.rumahpasta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cds.rumahpasta.Adapter.CartListAdapter;
import com.cds.rumahpasta.Database.SQLiteHelper;
import com.cds.rumahpasta.Models.Cart;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {

    ListView listView;
    public TextView totalPricetv;
    ArrayList<Cart>list = new ArrayList<Cart>();
    CartListAdapter adapter = null;
    SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        Button btnOrder = findViewById(R.id.btnPesan);
        listView = findViewById(R.id.listView);
        totalPricetv = findViewById(R.id.total);
        int total = 0;
        String totalPrice = null;

        list = new ArrayList<>();
        adapter = new CartListAdapter(this, R.layout.cart_item, list);
        listView.setAdapter(adapter);

        //get all data from sqlite
        Cursor cursor = helper.getData("SELECT ID, NAME, QUANTITY, PRICE FROM CART");
        Cursor cursor1 = helper.getData("SELECT * FROM CART");
        list.clear();
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String quantity = cursor.getString(2);
                String price = cursor.getString(3);
                Log.e("price: ", price);
                total = total + Integer.parseInt(price);
                Log.e("pricetotal: ", String.valueOf(total));
                list.add(new Cart(id, name, quantity, price));
            }while (cursor.moveToNext());
        }
        totalPricetv.setText(String.valueOf(total));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartListActivity.this);
                final int pos = position;
                builder.setTitle("Dialog Hapus").setMessage("Apakah Anda ingin menghapus item ini?").setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("CART ID =", list.get(pos).toString());
                        Cart cart = list.get(pos);
                        helper.deleteData(cart.getId());
                        list.remove(pos);
                        adapter.notifyDataSetChanged();
                        listView.invalidateViews();
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });builder.create();
                builder.show();
            }
        });

        //order
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() > 0 )
                    return;
                else
                    Toast.makeText(CartListActivity.this, "Keranjang Belanja Kosong", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setFocusable(true);
        adapter.notifyDataSetChanged();
    }
}
