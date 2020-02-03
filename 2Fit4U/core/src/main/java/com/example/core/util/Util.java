package com.example.core.util;

import android.content.Context;

import com.example.core.managers.DBManager;



public class Util {
    public static String calculateBMI(Context context)
    {
        String height = DBManager.getInstance().getData(Constants.DATA_TYPE_HEIGHT);
        String currentWeight = DBManager.getInstance().getData(Constants.DATA_TYPE_WEIGHT);

        try{
            return String.valueOf((Float.parseFloat(currentWeight)/(((Float.parseFloat(height)/100)*(Float.parseFloat(height)/100)))));
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return "No entry";
    }
}
