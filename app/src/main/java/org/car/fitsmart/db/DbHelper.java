package org.car.fitsmart.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NOMBRE = "ejercicios.db";
    public static final String TABLE_EJERCICIOS = "t_ejercicios";
    public static final String TABLE_PERSONA = "t_persona";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EJERCICIOS+ "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "nivel_dificultad TEXT," +
                "grupo_muscular TEXT NOT NULL,"+
                "num_series TEXT NOT NULL,"+
                "num_repes TEXT NOT NULL,"+
                "dia TEXT NOT NULL"+")");
        
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERSONA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL," +
                "altura TEXT NOT NULL," +
                "peso TEXT NOT NULL,"+
                "sexo TEXT NOT NULL,"+
                "peso_deseado TEXT NOT NULL,"+
                "edad TEXT NOT NULL"+")");
    }





    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EJERCICIOS);
        //sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PERSONA);
        /*sqLiteDatabase.execSQL("DROP TABLE " + TABLE_RUTINA);*/
        onCreate(sqLiteDatabase);
    }


}
