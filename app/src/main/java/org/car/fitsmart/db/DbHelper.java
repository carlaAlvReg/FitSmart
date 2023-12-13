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

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "ejercicios.db";
    public static final String TABLE_EJERCICIOS = "t_ejercicios";
    private static final String TABLE_RUTINA = "t_rutina";
    private static final String TABLE_ENTRENAMIENTO = "t_entrenamiento";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EJERCICIOS+ "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "nivel_dificultad TEXT," +
                "grupo_mucular TEXT NOT NULL)");
        
        /*sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_RUTINA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_ejercicio INTEGER NOT NULL," +
                "num_series INTEGER NOT NULL," +
                "num_reps INTEGER NOT NULL," +
                "nombre TEXT," +
                "FOREIGN KEY (id_ejercicio)REFERENCES TABLE_EJERCICIOS(id)" + ")");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ENTRENAMIENTO + "(" +
                "id_entrenamiento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_rutina INTEGER NOT NULL," +
                "nombre TEXT," +
                "FOREIGN KEY (id_rutina) REFERENCES TABLE_RUTINA(id)"+")");*/

       /* sqLiteDatabase.execSQL("INSERT INTO " + TABLE_EJERCICIOS + " (nombre, nivel_dificultad, grupo_muscular) VALUES " +
                "('Press de Banca' , 'Intermedio', 'Pecho')," +
                "('Sentadilla', 'Avanzado', 'Piernas'),"+
                "('Peso Muerto', 'Avanzado', 'Espalda y Piernas'),"+
                "('Fondos en Paralelas', 'Intermedio', 'Tríceps y Pecho'),"+
                "('Pull-Ups', 'Intermedio', 'Espalda'),"+
                "('Dominadas', 'Avanzado', 'Espalda'),"+
                "('Plancha', 'Intermedio', 'Core'),"+
                "('Curl de Bíceps', 'Principiante', 'Bíceps'),"+
                "('Press Militar', 'Intermedio', 'Hombros'),"+
                "('Prensa de Piernas', 'Principiante', 'Piernas'),"+
                "('Crunches', 'Principiante', 'Abdominales'),"+
                "('Zancadas', 'Intermedio', 'Piernas'),"+
                "('Remo con Barra', 'Intermedio', 'Espalda'),"+
                "('Elevaciones Laterales', 'Principiante', 'Hombros'),"+
                "('Burpees', 'Avanzado', 'Cuerpo Completo'),"+
                "('Mountain Climbers', 'Intermedio', 'Cardio'),"+
                "('Flexiones de Tríceps', 'Principiante', 'Tríceps'),"+
                "('Hip Thrust', 'Intermedio', 'Glúteos'),"+
                "('Elevación de Talones', 'Principiante', 'Gemelos'),"+
                "('Press de Hombros con Mancuernas', 'Intermedio', 'Hombros'),"+
                "('Abdominales Rusos', 'Principiante', 'Abdominales'),"+
                "('Deadlift Rumano', 'Intermedio', 'Espalda y Piernas'),"+
                "('Curl Martillo', 'Principiante', 'Bíceps'),"+
                "('Paseo del Granjero', 'Avanzado', 'Cuerpo Completo'),"+
                "('Press Inclinado', 'Intermedio', 'Pecho'),"+
                "('Sentadilla Frontal', 'Avanzado', 'Piernas'),"+
                "('Peso Muerto Sumo', 'Avanzado', 'Espalda y Piernas'),"+
                "('Dips', 'Intermedio', 'Tríceps y Pecho'),"+
                "('Chin-Ups', 'Intermedio', 'Espalda'),"+
                "('Pull-Ups Agarre Ancho', 'Avanzado', 'Espalda'),"+
                "('Plancha Lateral', 'Intermedio', 'Core'),"+
                "('Curl de Concentración', 'Principiante', 'Bíceps'),"+
                "('Face Pull', 'Intermedio', 'Hombros'),"+
                "('Extensiones de Cuádriceps en Máquina', 'Principiante', 'Piernas'),"+
                "('Hanging Leg Raises', 'Intermedio', 'Abdominales'),"+
                "('Step-Ups', 'Intermedio', 'Piernas'),"+
                "('Rowing con Cable', 'Intermedio', 'Espalda'),"+
                "('Shrugs', 'Principiante', 'Trapecios'),"+
                "('Skipping', 'Intermedio', 'Cardio'),"+
                "('Flexiones de Pecho', 'Principiante', 'Pecho'),"+
                "('Glute Bridge', 'Principiante', 'Glúteos'),"+
                "('Elevación de Talones Sentado', 'Principiante', 'Gemelos'),"+
                "('Press Arnold', 'Intermedio', 'Hombros'),"+
                "('Oblique Crunches', 'Principiante', 'Abdominales'),"+
                "('Lunges', 'Intermedio', 'Piernas'),"+
                "('Lat Pulldown', 'Intermedio', 'Espalda'),"+
                "('Elevaciones Frontales', 'Principiante', 'Hombros'),"+
                "('Jump Squats', 'Avanzado', 'Piernas'),"+
                "('Tricep Kickbacks', 'Principiante', 'Tríceps'),"+
                "('Hip Abduction', 'Intermedio', 'Glúteos'),"+
                "('Press de Banca Declinado', 'Intermedio', 'Pecho'),"+
                "('Sentadilla Búlgara', 'Avanzado', 'Piernas'),"+
                "('Zancadas Laterales', 'Avanzado', 'Piernas'),"+
                "('Fondos en Anillas', 'Avanzado', 'Pecho y Tríceps'),"+
                "('Pull-Ups con Peso', 'Avanzado', 'Espalda'),"+
                "('Dragon Flags', 'Avanzado', 'Abdominales'),"+
                "('Curl de Bíceps con Barra Z', 'Intermedio', 'Bíceps'),"+
                "('Clean and Jerk', 'Avanzado', 'Cuerpo Completo'),"+
                "('Prensa Militar con Mancuernas', 'Intermedio', 'Hombros'),"+
                "('Prensa de Piernas Inclinada', 'Intermedio', 'Piernas'),"+
                "('Hollow Body Hold', 'Principiante', 'Core'),"+
                "('Leg Curl', 'Intermedio', 'Isquiotibiales'),"+
                "('Pull-Ups Agarre Supino', 'Intermedio', 'Espalda'),"+
                "('Elevaciones de Rodillas', 'Principiante', 'Abdominales'),"+
                "('Box Jumps', 'Intermedio', 'Piernas'),"+
                "('Pike Push-Ups', 'Principiante', 'Hombros'),"+
                "('Reverse Lunges', 'Intermedio', 'Piernas'),"+
                "('Russian Twists', 'Principiante', 'Abdominales'),"+
                "('Calf Raises', 'Principiante', 'Gemelos'),"+
                "('Flutter Kicks', 'Principiante', 'Abdominales'),"+
                "('Side Plank Hip Dips', 'Intermedio', 'Core'),"+
                "('Plank to Push-Up', 'Intermedio', 'Cuerpo Completo'),"+
                "('Seated Leg Press', 'Principiante', 'Piernas'),"+
                "('Bent Over Row', 'Intermedio', 'Espalda'),"+
                "('Tricep Dips', 'Principiante', 'Tríceps')," +
                "('Leg Extension', 'Principiante', 'Cuádriceps')");*/

    }

    /*@SuppressLint("Range")
    public List<Ejercicio> getAllEjercicios() {
        List<Ejercicio> ejercicios = new ArrayList<>();
        String selectQuery = "SELECT * FROM t_ejercicios";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Ejercicio ejercicio = new Ejercicio();
                ejercicio.setId(cursor.getInt(cursor.getColumnIndex("id")));
                ejercicio.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                ejercicio.setNivel_dificultad(cursor.getString(cursor.getColumnIndex("nivel_dificultad")));
                ejercicio.setGrupo_mucular(cursor.getString(cursor.getColumnIndex("grupo_mucular")));

                ejercicios.add(ejercicio);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ejercicios;
    }*/



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EJERCICIOS);
        /*sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ENTRENAMIENTO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_RUTINA);*/
        onCreate(sqLiteDatabase);
    }

    /*public void insertRutina(SQLiteDatabase sqLiteDatabase ,int id_ejercicio, int num_series, int num_reps, String nombre) {
        ContentValues values = new ContentValues();
        values.put("id_ejercicio", id_ejercicio);
        values.put("num_series", num_series);
        values.put("num_reps", num_reps);
        values.put("nombre", nombre);
        sqLiteDatabase.insert("t_rutina", null, values);
    }*/
}
