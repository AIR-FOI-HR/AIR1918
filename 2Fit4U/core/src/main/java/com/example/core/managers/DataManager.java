package com.example.core.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.core.interfaces.DataPresenter;

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
        //TODO
        // DBManager dohvat podataka iz baze
        module.setHipData(new List<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(@Nullable Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String get(int index) {
                return null;
            }

            @Override
            public String set(int index, String element) {
                return null;
            }

            @Override
            public void add(int index, String element) {

            }

            @Override
            public String remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<String> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<String> subList(int fromIndex, int toIndex) {
                return null;
            }
        });
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
