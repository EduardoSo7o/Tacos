package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tacos.exceptions.NullReceiptException;

public class totalScreen extends AppCompatActivity {

    private  float tacoPrice;
    private  float tortaPrice;
    private  float refrescoPrice;
    private TextView tvExchange;
    private EditText etReceipt;
    private float total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_screen);

        TextView tvTaco = findViewById(R.id.tv_tacoNumber);
        TextView tvTorta = findViewById(R.id.tv_tortaNumber);
        TextView tvRefresco = findViewById(R.id.tv_refrescoNumber);
        TextView tvTotal = findViewById(R.id.tv_totalNumber);
        tvExchange = findViewById(R.id.tv_exchangeNumber);
        etReceipt = findViewById(R.id.et_receipt);

        SharedPreferences sharedPreferences = getSharedPreferences("prices", Context.MODE_PRIVATE);
        tacoPrice = sharedPreferences.getFloat("taco", 0);
        tortaPrice = sharedPreferences.getFloat("torta", 0);
        refrescoPrice = sharedPreferences.getFloat("refresco", 0);

        int taco = getIntent().getIntExtra("taco", 0);
        int torta = getIntent().getIntExtra("torta", 0);
        int refresco = getIntent().getIntExtra("refresco", 0);

        tvTaco.setText(String.valueOf(taco * tacoPrice) + "$");
        tvTorta.setText(String.valueOf(torta * tortaPrice) + "$");
        tvRefresco.setText(String.valueOf(refresco * refrescoPrice) + "$");

        total = taco * tacoPrice + torta * tortaPrice + refresco * refrescoPrice;
        tvTotal.setText(String.valueOf(total) + "$");

    }

    public void exchange(View view){
        String stringReceipt = etReceipt.getText().toString();
        try {
            if(stringReceipt.equals(""))
                throw new NullReceiptException();

            float receipt = Float.parseFloat(stringReceipt);
            float exchange = receipt - total;

            tvExchange.setText(String.valueOf(exchange) + "$");
        } catch (NullReceiptException e) {
            Toast.makeText(this, "Ingresa el dinero recibido", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view){
        finish();
    }
}