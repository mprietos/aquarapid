package com.aquarapid.app.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

public class SessionHelper {

    public static final String PREFERENCE_KEY_IS_LOGIN = "islogged" ;
    public static final String PREFERENCE_KEY_CIF= "cif" ;
    public static final String PREFERENCE_KEY_NAME = "name" ;
    public static final String PREFERENCE_KEY_IS_ADMIN = "isadmin" ;
    private static String FORCE_LOGOUT = "forcelogout";
    private SharedPreferences pref;
    // Editor for Shared preferences
    private SharedPreferences.Editor editor;
    private Context _context;
    int PRIVATE_MODE = 0;

    public SessionHelper(Context context){
        this._context = context;
        pref = _context.getSharedPreferences("aquarapid", PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserSession(String name, String cif, boolean isAdmin){
        editor.putBoolean(this.PREFERENCE_KEY_IS_LOGIN, true);
        editor.putString(this.PREFERENCE_KEY_NAME, name);
        editor.putString(this.PREFERENCE_KEY_CIF, cif);
        editor.putBoolean(this.PREFERENCE_KEY_IS_ADMIN, isAdmin);
        editor.commit();
    }

    public boolean isLoggedIn(){
        boolean forceClose = pref.getBoolean(FORCE_LOGOUT, true);
        if (forceClose){
            logoutUser();
            editor.putBoolean(FORCE_LOGOUT,false);

            editor.commit();
            return false;
        }else {


            return pref.getBoolean(this.PREFERENCE_KEY_IS_LOGIN, false);
        }
    }

    public String getName(){
        return pref.getString(this.PREFERENCE_KEY_NAME, "");

    }

    public boolean isAdmin(){
        return pref.getBoolean(this.PREFERENCE_KEY_IS_ADMIN, false);
    }
    public void setName(String t) {
        editor.putString(this.PREFERENCE_KEY_NAME, t);
        editor.commit();
    }

    public String getCIF(){
        return pref.getString(this.PREFERENCE_KEY_CIF, "");

    }

    public void setCIF(String t) {
        editor.putString(this.PREFERENCE_KEY_CIF, t);
        editor.commit();
    }
    public void logoutUser() {

        this.setName("");
        this.setCIF("");
        editor.putBoolean(this.PREFERENCE_KEY_IS_LOGIN, false);
        editor.putBoolean(this.PREFERENCE_KEY_IS_ADMIN, false);
        editor.commit();
    }

}
