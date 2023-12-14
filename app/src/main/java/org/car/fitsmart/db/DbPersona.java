package org.car.fitsmart.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbPersona extends DbHelper{
    Context context;

    public DbPersona(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertarPersona(String nombre, String altura, String peso, String sexo, String pesoDeseado, String edad) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("altura", altura);
            values.put("peso", peso);
            values.put("sexo", sexo);
            values.put("peso_deseado", pesoDeseado);
            values.put("edad",edad);

            id = db.insert(TABLE_PERSONA, null, values);
        }catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Persona> mostrarPersna(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Persona> listaPersonas = new ArrayList<>();
        Persona persona = null;
        Cursor cursorPersna = null;

        cursorPersna = db.rawQuery("SELECT * FROM " + TABLE_PERSONA, null);

        if(cursorPersna.moveToFirst()){
            do{
                persona = new Persona();
                persona.setId(cursorPersna.getInt(0));
                persona.setNombre(cursorPersna.getString(1));
                persona.setAltura(cursorPersna.getString(2));
                persona.setPeso(cursorPersna.getString(3));
                persona.setSexo(cursorPersna.getString(4));
                persona.setPesoDeseado(cursorPersna.getString(5));
                persona.setEdad(cursorPersna.getString(6));
                listaPersonas.add(persona);
            }while(cursorPersna.moveToNext());
        }

        cursorPersna.close();
        db.close();

        return listaPersonas;
    }

    public Persona verPersonas(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Persona persona = null;
        Cursor cursorPersona = null;

        cursorPersona = db.rawQuery("SELECT * FROM " + TABLE_PERSONA + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorPersona.moveToFirst()){
            persona = new Persona();
            persona.setId(cursorPersona.getInt(0));
            persona.setNombre(cursorPersona.getString(1));
            persona.setAltura(cursorPersona.getString(2));
            persona.setPeso(cursorPersona.getString(3));
            persona.setSexo(cursorPersona.getString(4));
            persona.setPesoDeseado(cursorPersona.getString(5));
            persona.setEdad(cursorPersona.getString(6));
        }

        cursorPersona.close();
        db.close();

        return persona;
    }
    public boolean editarPersona(int id, String nombre, String altura, String peso, String sexo, String pesoDeseado, String edad){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_PERSONA + " SET nombre = '"+nombre+"', altura = '"+altura+"', peso = '"+peso+"',sexo = '"+sexo+"',peso_deseado = '"+pesoDeseado+"',edad = '"+edad+"' WHERE id ='"+id+ "'  ");
            correcto=true;
        }catch (Exception ex) {
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
    public boolean eliminarPersona(int id){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_PERSONA + " WHERE id = '" + id + "'");
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
