package com.example.airelibre_login_y_registro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    public static final String DBlibre = "Login.db";

    public BDHelper(Context context) {
        super(context, "Login.db", null,1);
    }

    @Override
    public void onCreate (SQLiteDatabase MiDB){
        MiDB.execSQL("create Table usuarios (usuario TEXT primary key, contrasena TEXT)");
    }

    @Override
    public void onUpgrade (SQLiteDatabase MiDB, int i, int ii) {
        MiDB.execSQL ("drop Table if exists usuarios");
    }

    public Boolean insertarData (String usuario, String contrasena){
        SQLiteDatabase MiDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put ("Usuario", usuario);
        contentValues.put ("contrasena", contrasena);
        long results = MiDB.insert (   "Usuarios", null, contentValues);
        if (results ==-1) return false;
        else
            return true;
    }

    public Boolean verificarusuario (String usuario) {
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from Usuarios where usuario = ?", new String[] {usuario});
        if (cursor.getCount() >0)
            return true;
        else
            return false;
    }

    public Boolean verificarusuariocontrasena (String usuario, String contrasena) {
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from Usuarios where usuario = ? and contrasena = ?", new String[] {usuario,contrasena});
        if (cursor.getCount() >0)
            return true;
        else
        return false;
    }
}
