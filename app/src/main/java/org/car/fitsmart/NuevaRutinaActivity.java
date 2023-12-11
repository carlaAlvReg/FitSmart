package org.car.fitsmart;

import android.app.Activity;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import org.car.fitsmart.db.DbHelper;
import org.car.fitsmart.db.Ejercicio;

import java.util.List;

public class NuevaRutinaActivity extends Activity {


    DbHelper dbHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_rutina);


        ImageButton btInserta = findViewById(R.id.bNuevaRutina);
        btInserta.setOnClickListener(view -> {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if(db != null){
                Toast.makeText(NuevaRutinaActivity.this, "BASE DE DATOS CREADA CORRECTAMENTE", Toast.LENGTH_LONG).show();
            }

            List<Ejercicio> ejercicios = dbHelper.getAllEjercicios();

            if (!ejercicios.isEmpty()) {
                // Crear una lista de nombres de ejercicios para la lista de selección
                CharSequence[] nombresEjercicios = new CharSequence[ejercicios.size()];
                for (int i = 0; i < ejercicios.size(); i++) {
                    nombresEjercicios[i] = ejercicios.get(i).getNombre();
                }

                // Construir el diálogo de selección de ejercicios
                AlertDialog.Builder builder = new AlertDialog.Builder(NuevaRutinaActivity.this);
                builder.setTitle("Selecciona un ejercicio");

                builder.setItems(nombresEjercicios, (dialog, which) -> {
                    // El usuario ha seleccionado un ejercicio
                    Ejercicio ejercicioSeleccionado = ejercicios.get(which);

                    // Obtener el id del ejercicio seleccionado
                    int idEjercicioSeleccionado = ejercicioSeleccionado.getId();

                    // Puedes hacer algo con el ejercicio seleccionado aquí
                    // Por ejemplo, agregarlo a la tabla de rutinas llamando al método insertRutina
                    int numSeries = 3;  // Puedes ajustar estos valores según tus necesidades
                    int numReps = 12;
                    String nombreRutina = "Nombre de la rutina";


                    // Insertar la rutina en la base de datos
                    dbHelper.insertRutina(db, idEjercicioSeleccionado, numSeries, numReps, nombreRutina);

                    // Cerrar la base de datos
                    db.close();

                    // Puedes mostrar un mensaje de confirmación
                    Toast.makeText(NuevaRutinaActivity.this, "Rutina agregada correctamente", Toast.LENGTH_LONG).show();
                });

                builder.show();
            } else {
                Toast.makeText(NuevaRutinaActivity.this, "No hay ejercicios en la base de datos", Toast.LENGTH_LONG).show();
            }
        });


        ListView listView = findViewById(R.id.lvListaNR);

        ArrayAdapter<Ejercicio> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice);
        listView.setAdapter(adapter);


        List<Ejercicio> ejercicios = dbHelper.getAllEjercicios();
        for (Ejercicio ejercicio : ejercicios) {
            adapter.add(ejercicio);
        }

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Ejercicio ejercicioSeleccionado = (Ejercicio) parent.getItemAtPosition(position);

        });

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ejercicio ejercicioSeleccionado = (Ejercicio) parent.getItemAtPosition(position);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.insertRutina(db,ejercicioSeleccionado.getId(), 3, 8, "Rutina 1");
    }




}



