package com.example.tacos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tacos.exceptions.*;
import com.example.tacos.foods.*;

public class MainActivity extends AppCompatActivity {

    Taco taco;
    Torta torta;
    Refresco refresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taco = new Taco((EditText) findViewById(R.id.et_taco));
        torta = new Torta((EditText) findViewById(R.id.et_torta));
        refresco = new Refresco((EditText) findViewById(R.id.et_refresco));

        SharedPreferences sharedPreferences = getSharedPreferences("prices", Context.MODE_PRIVATE);
        if(sharedPreferences.getFloat("taco", 0) <= 0 || sharedPreferences.getFloat("torta", 0) <= 0 || sharedPreferences.getFloat("refresco", 0) <= 0){
            Intent intent = new Intent(this, PricesActivity.class);
            startActivity(intent);
        }
    }

    public void plus(View view){
        Food food = null;

        switch (view.getId()){
            case R.id.btn_tacoPlus:
                food = taco;
                break;
            case R.id.btn_tortaPlus:
                food = torta;
                break;
            case R.id.btn_refrescoPlus:
                food = refresco;
                break;
        }

        int units = food.getUnits();

        units ++;
        food.setUnits(units);
    }

    public void minus(View view){
        Food food = null;

        switch (view.getId()){
            case R.id.btn_tacoMinus:
                food = taco;
                break;
            case R.id.btn_tortaMinus:
                food = torta;
                break;
            case R.id.btn_refrescoMinus:
                food = refresco;
                break;
        }

        int units = food.getUnits();

        if(units > 0){
            units --;
            food.setUnits(units);
        }
    }

    public void count(View view){

        int tacoUnits = taco.getUnits();
        int tortaUnits = torta.getUnits();
        int refrescoUnits = refresco.getUnits();

        try {
            if (tacoUnits == 0 && tortaUnits == 0 && refrescoUnits == 0)
                throw new NotElementSelectedException();

            Intent intent = new Intent(this, totalScreen.class);

            intent.putExtra("taco", tacoUnits);
            intent.putExtra("torta", tortaUnits);
            intent.putExtra("refresco", refrescoUnits);

            startActivity(intent);
        } catch (NotElementSelectedException e) {
            Toast.makeText(this, "Registra al menos un producto", Toast.LENGTH_SHORT).show();
        }
    }

    public void setZero(View view){
        taco.setUnits(0);
        torta.setUnits(0);
        refresco.setUnits(0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prices, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        if(id == R.id.ab_prices){
            Intent intent = new Intent(this, PricesActivity.class);
            startActivity(intent);
            return true;
        }
        return  super.onOptionsItemSelected(menuItem);
    }
}