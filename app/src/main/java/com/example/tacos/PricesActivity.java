package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tacos.exceptions.NotElementSelectedException;
import com.example.tacos.foods.Refresco;
import com.example.tacos.foods.Torta;

public class PricesActivity extends AppCompatActivity {

    private float tacoPrice;
    private float tortaPrice;
    private float refrescoPrice;
    private EditText etTaco;
    private EditText etTorta;
    private EditText etRefresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        etTaco = findViewById(R.id.et_tacoPrice);
        etTorta = findViewById(R.id.et_tortaPrice);
        etRefresco = findViewById(R.id.et_refrescoPrice);

        SharedPreferences sharedPreferences = getSharedPreferences("prices", Context.MODE_PRIVATE);
        tacoPrice = sharedPreferences.getFloat("taco", 0);
        tortaPrice = sharedPreferences.getFloat("torta", 0);
        refrescoPrice = sharedPreferences.getFloat("refresco", 0);

        etTaco.setHint(String.valueOf(tacoPrice));
        etTorta.setHint(String.valueOf(tortaPrice));
        etRefresco.setHint(String.valueOf(refrescoPrice));
    }

    public void cancel(View view){
        finish();
    }

    public void savePrices(View view){

        String tacoString = etTaco.getText().toString();
        String tortaString = etTorta.getText().toString();
        String refrescoString = etRefresco.getText().toString();

        try {
            if(tacoString.equals("") && tortaString.equals("") && refrescoString.equals(""))
                throw new NotElementSelectedException();

            if (!tacoString.equals(""))
                tacoPrice = Float.parseFloat(tacoString);
            if (!tortaString.equals(""))
                tortaPrice = Float.parseFloat(tortaString);
            if (!refrescoString.equals(""))
                refrescoPrice = Float.parseFloat(refrescoString);

            SharedPreferences sharedPreferences = getSharedPreferences("prices", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putFloat("taco", tacoPrice);
            editor.putFloat("torta", tortaPrice);
            editor.putFloat("refresco", refrescoPrice);

            editor.commit();

            Toast.makeText(this, "Precios guardados", Toast.LENGTH_SHORT).show();
        } catch (NotElementSelectedException e) {
            Toast.makeText(this, "No se ha modificado ningún precio", Toast.LENGTH_SHORT).show();
        }
    }
}