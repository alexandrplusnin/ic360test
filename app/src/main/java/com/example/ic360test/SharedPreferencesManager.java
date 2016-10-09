package com.example.ic360test;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alexandr on 09.10.2016.
 * <p>
 * Вспомогательный класс для работы с SharedPreferences
 */
class SharedPreferencesManager {

    /**
     * Название SharedPreferences
     */
    private static final String PREFS_NAME = "cached_data";

    /**
     * @param context Context
     * @param var     название параметра
     * @param defVal  значение параметра по умолчанию
     * @return значение параметра
     */
    static String getValue(Context context, String var, String defVal) {
        SharedPreferences prefs = getPrefs(context);
        if (prefs != null) {
            return prefs.getString(var, defVal);
        }
        return null;
    }

    /**
     * Устанавливает значение переменной в SharedPreferences.
     *
     * @param context Context
     * @param var     название параметра
     * @param val     значение параметра
     */
    static void setValue(Context context, String var, String val) {
        SharedPreferences prefs = getPrefs(context);
        if (prefs != null) {
            prefs.edit().putString(var, val).commit();
        }
    }

    /**
     * Очищает все данные из SharedPreferences
     *
     * @param context Context
     */
    static void clear(Context context) {
        SharedPreferences prefs = getPrefs(context);
        if (prefs != null) {
            prefs.edit().clear().commit();
        }
    }

    /**
     * @param context Context
     * @return объект SharedPreferences
     */
    private static SharedPreferences getPrefs(Context context) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
