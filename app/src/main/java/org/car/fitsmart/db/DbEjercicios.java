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

    public long insertaEjercicio(String nombre, String nivel_dificultad, String grupo_muscular, String num_series, String num_repes, String dia){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("nivel_dificultad", nivel_dificultad);
            values.put("grupo_muscular", grupo_muscular);
            values.put("num_series", num_series);
            values.put("num_repes", num_repes);
            values.put("dia",dia);

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
                ejercicio.setNum_series(cursorEjercicios.getString(4));
                ejercicio.setNum_repes(cursorEjercicios.getString(5));
                ejercicio.setDia(cursorEjercicios.getString(6));
                listaEjercicios.add(ejercicio);
            }while(cursorEjercicios.moveToNext());
        }

        cursorEjercicios.close();
        db.close();

        return listaEjercicios;
    }

    public Ejercicio verEjercicios(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Ejercicio ejercicio = null;
        Cursor cursorEjercicios = null;

        cursorEjercicios = db.rawQuery("SELECT * FROM " + TABLE_EJERCICIOS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorEjercicios.moveToFirst()){
                ejercicio = new Ejercicio();
                ejercicio.setId(cursorEjercicios.getInt(0));
                ejercicio.setNombre(cursorEjercicios.getString(1));
                ejercicio.setNivel_dificultad(cursorEjercicios.getString(2));
                ejercicio.setGrupo_muscular(cursorEjercicios.getString(3));
                ejercicio.setNum_series(cursorEjercicios.getString(4));
                ejercicio.setNum_repes(cursorEjercicios.getString(5));
                ejercicio.setDia(cursorEjercicios.getString(6));
        }

        cursorEjercicios.close();
        db.close();

        return ejercicio;
    }
    public boolean editarEjercicio(int id, String nombre, String nivel_dificultad, String grupo_muscular, String num_series, String num_repes, String dia){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_EJERCICIOS + " SET nombre = '"+nombre+"', nivel_dificultad = '"+nivel_dificultad+"', grupo_muscular = '"+grupo_muscular+"',num_series = '"+num_series+"',num_repes = '"+num_repes+"',dia = '"+dia+"' WHERE id ='"+id+ "'  ");
            correcto=true;
        }catch (Exception ex) {
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
    public boolean eliminarEjercicio(int id){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_EJERCICIOS + " WHERE id = '" + id + "'");
            correcto=true;
        }catch (Exception ex) {
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }

}
