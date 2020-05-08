package com.cds.rumahpasta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pesanApps = findViewById(R.id.btn_pesan_apps);
        pesanApps.setOnClickListener(this);
        Button pesanPhone = findViewById(R.id.btn_pesan_phone);
        pesanPhone.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_pesan_apps:
                Intent moveIntent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_pesan_phone:
                String phoneNumber = "+6281246206146";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialPhoneIntent);
                break;
        }
    }
}
