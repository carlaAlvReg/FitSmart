package org.car.fitsmart;

import android.app.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.car.fitsmart.db.DbHelper;
import org.car.fitsmart.db.Ejercicio;

import java.util.ArrayList;

public class NuevaRutinaActivity extends Activity {

    Button btnCrear;
    Button btnRegistro;
   /* DbHelper dbHelper = new DbHelper(this);*/
    RecyclerView listaEjercicios;
    ArrayList<Ejercicio> listaArrayEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_rutina);
        btnCrear = findViewById(R.id.btnCrear);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(NuevaRutinaActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(NuevaRutinaActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(NuevaRutinaActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        /*listaEjercicios = findViewById(R.id.listaEjercicios);
        listaEjercicios.setLayoutManager(new LinearLayoutManager(this));

        DbEjercicios dbEjercicios = new DbEjercicios(NuevaRutinaActivity.this);

        listaArrayEjercicios = new ArrayList<>();

        ListaEjerciciosAdapter adapter = new ListaEjerciciosAdapter(dbEjercicios.mostrarEjercicios());
        listaEjercicios.setAdapter(adapter);*/


       /* ImageButton btInserta = findViewById(R.id.bNuevaRutina);
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

        });*/

    }

    /*public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nueva_rutina, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int itemId = item.getItemId();

        if (itemId == R.id.menuNuevo) {
            nuevoRegistro();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }*/
    
    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

   /* public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Ejercicio ejercicioSeleccionado = (Ejercicio) parent.getItemAtPosition(position);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.insertRutina(db,ejercicioSeleccionado.getId(), 3, 8, "Rutina 1");
    }*/




}



