package com.example.a2018;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a2018.bd.Bd;

public class Conexion extends SQLiteOpenHelper {

    public Conexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(Bd.CREAR_TABLA_USUARIOS);
        bd.execSQL(Bd.CREAR_TABLA_ROLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE IF EXISTS "+ Bd.TABLA_USUARIOS);
        bd.execSQL("DROP TABLE IF EXISTS "+Bd.TABLA_ROLES);
        onCreate(bd);
    }
}
