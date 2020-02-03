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

    public void syncData()
    {
        //TODO
        // s accoumtManager azuriraj spremljene podatke iz DBManager
        /*DataLoader dataLoader = DataLoaderFactory.getDataLoader();
        dataLoader.loadData(new DataLoadedListener() {
            @Override
            public void onDataLoaded(List<Store> stores, List<Discount> discounts) {
                //sync local database data
                DAO dao = database.getDAO();
                dao.deleteStores();
                dao.deleteDiscounts();

                for(Store s: stores)
                    dao.insertStores(s);
                for(Discount d: discounts)
                    dao.insertDiscounts(d);
            }
        });*/
    }
}
