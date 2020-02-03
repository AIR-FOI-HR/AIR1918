package com.example.core.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.core.interfaces.DataPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DataManager {
    private static final DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    //TODO
    // mozda prebaci ovo u app?
    private DataManager() {
    }

    public void sendData(DataPresenter module)
    {

        module.setWeightData(DBManager.getInstance().getData());
        module.setWaistData(DBManager.getInstance().getWaistData());
        module.setHipData(DBManager.getInstance().getHipData());
        module.setNeckData(DBManager.getInstance().getNeckData());
    }


}
