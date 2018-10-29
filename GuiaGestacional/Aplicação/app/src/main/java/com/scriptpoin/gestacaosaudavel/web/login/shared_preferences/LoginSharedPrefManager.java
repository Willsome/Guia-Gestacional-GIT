package com.scriptpoin.gestacaosaudavel.web.login.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginSharedPrefManager {
    private static LoginSharedPrefManager mInstance;
    private static Context mCtx;

    private static String SHARED_PREF_NAME = "sp_login";
    private static String KEY_ID = "id_usuario";
    private static String KEY_USUARIO = "usuario";

    private LoginSharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized LoginSharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LoginSharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean login(int id, String usuario) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID, id);
        editor.putString(KEY_USUARIO, usuario);

        editor.apply();

        return true;
    }

    public boolean isUsuarioLogado() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USUARIO, null) != null) {
            return true;
        }

        return false;
    }

    public boolean logout() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        return true;
    }

    public int getId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ID, 0);
    }

    public String getUsuario() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USUARIO, null);
    }

//    public String getEmail() {
//
//    }
}
