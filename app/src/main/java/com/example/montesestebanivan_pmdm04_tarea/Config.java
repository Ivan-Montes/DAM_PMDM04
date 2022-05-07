package com.example.montesestebanivan_pmdm04_tarea;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class Config extends PreferenceFragmentCompat {
    //https://www.geeksforgeeks.org/how-to-implement-preferences-settings-screen-in-android/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.config);
    }

   @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {}




}
