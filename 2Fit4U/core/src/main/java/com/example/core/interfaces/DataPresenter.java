package com.example.core.interfaces;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;

import java.util.List;

public interface DataPresenter {
    Fragment getFragment();
    Drawable getIcon(Context context);
    String getName(Context context);
    void setWeightData(List<String> weights);
    void setHipData(List<String> hips);
    void setNeckData(List<String> necks);
    void setWaistData(List<String> waists);
}
