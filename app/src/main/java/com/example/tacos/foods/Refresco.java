package com.example.tacos.foods;

import android.view.View;
import android.widget.EditText;

import com.example.tacos.R;

public class Refresco implements Food {
    private static Refresco instance;
    private int units;
    private EditText et;

    public Refresco(EditText editText){
        et = editText;
        units = 0;
    }

    @Override
    public int getUnits() {
        String unitsString = et.getText().toString();
        if(!unitsString.equals(""))
            units = Integer.parseInt(unitsString);
        else
            units = 0;

        return units;
    }

    @Override
    public void setUnits(int units) {
        this.units = units;

        if (units == 0) {
            et.setText("");
        } else {
            et.setText(String.valueOf(units));
        }
    }
}
