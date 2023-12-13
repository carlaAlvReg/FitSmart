package org.car.fitsmart.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbEjercicios extends DbHelper{

    Context context;
    public DbEjercicios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaEjercicio(String nombre, String nivel_dificultad, String grupo_muscular){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("nivel_dificultad", nivel_dificultad);
            values.put("grupo_muscular", grupo_muscular);

            id = db.insert(TABLE_EJERCICIOS, null, values);
        }catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Ejercicio> mostrarEjercicios(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
        Ejercicio ejercicio = null;
        Cursor cursorEjercicios = null;

        cursorEjercicios = db.rawQuery("SELECT * FROM " + TABLE_EJERCICIOS, null);

        if(cursorEjercicios.moveToFirst()){
            do{
                ejercicio = new Ejercicio();
                ejercicio.setId(cursorEjercicios.getInt(0));
                ejercicio.setNombre(cursorEjercicios.getString(1));
                ejercicio.setNivel_dificultad(cursorEjercicios.getString(2));
                ejercicio.setGrupo_muscular(cursorEjercicios.getString(3));
                listaEjercicios.add(ejercicio);
            }while(cursorEjercicios.moveToNext());
        }

        cursorEjercicios.close();
        db.close();

        return listaEjercicios;
    }
}
