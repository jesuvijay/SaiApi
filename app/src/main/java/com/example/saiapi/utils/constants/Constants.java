package com.example.saiapi.utils.constants;

import android.content.Context;

import com.example.saiapi.utils.preference.Defaults;
import com.example.saiapi.utils.preference.Keys;
import com.example.saiapi.utils.preference.Prefs;

public class Constants {
   public static String getJwtToken(Context context) {
        Prefs.init(context);
        return Prefs.getStrPref(Keys.JWT_TOKEN, Defaults.JWT_TOKEN);
    }


}
