package com.luobo.wanandroid.ui.setting;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.luobo.wanandroid.R;
import com.luobo.wanandroid.dialog.SimpleDialog;

public class SettingFragment extends PreferenceFragmentCompat {
    String TAG = this.getClass().getName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference logout = findPreference(getString(R.string.logout));
        logout.setOnPreferenceClickListener(preference -> {
            SimpleDialog logoutDialog = new SimpleDialog();
            logoutDialog.setMessage(getString(R.string.logout_message));
            logoutDialog.show(getChildFragmentManager(),"login_out");
          /*  SharedPreferences sharedPreferences = getContext().getSharedPreferences("CookiePersistence", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();*/
            Log.e(TAG, "onPreferenceClick: ");
            return true;
        });
    }

    /*@Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == findPreference(getString(R.string.logout)))
        {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("CookiePersistence", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
            Log.e(TAG, "onPreferenceClick: ");
        }
        return true;
    }*/
}
