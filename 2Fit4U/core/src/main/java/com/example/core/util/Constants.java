package com.example.core.util;

import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;

public class Constants {

    public static final int DATA_TYPE_WEIGHT = 1;
    public static final int DATA_TYPE_NECK = 2;
    public static final int DATA_TYPE_WAIST = 3;
    public static final int DATA_TYPE_HIPS = 4;
    public static final int DATA_TYPE_HEIGHT = 5;
    public static final int DATA_TYPE_STARTING_WEIGHT = 6;
    public static final int DATA_TYPE_STARTING_NECK = 7;
    public static final int DATA_TYPE_STARTING_WAIST = 8;
    public static final int DATA_TYPE_STARTING_HIPS = 9;
    public static final int DATA_TYPE_GOAL_WEIGHT = 10;
    public static final int DATA_TYPE_MEASUREMENTS = 11;
    public static final int DATA_TYPE_NFC = 12;

    //NFC
    public static final String[][] NFC_TECH_LIST = new String[][] {
            new String[] {
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    public static final int NAVIGATION_SETTINGS = 10;
    public static final int NAVIGATION_LOGOUT = 11;
}
