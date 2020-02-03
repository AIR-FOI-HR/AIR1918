package com.example.core.interfaces;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;

import com.example.core.items.HipItem;
import com.example.core.items.NeckItem;
import com.example.core.items.WaistItem;
import com.example.core.items.WeightItem;

import java.util.List;

public interface DataPresenter {
    Fragment getFragment();
    Drawable getIcon(Context context);
    String getName(Context context);
    void setWeightData(List<WeightItem> weights);
    void setHipData(List<HipItem> hips);
    void setNeckData(List<NeckItem> necks);
    void setWaistData(List<WaistItem> waists);
}
