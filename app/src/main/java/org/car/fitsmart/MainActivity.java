package org.car.fitsmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.car.fitsmart.adaptadores.ListaEjerciciosAdapter;
import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.DbHelper;
import org.car.fitsmart.db.DbPersona;
import org.car.fitsmart.db.Ejercicio;
import org.car.fitsmart.db.Persona;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //Button btnCrear;
    EditText etPeso, etEdad, etPesoDeseado, etAltura;
    RecyclerView lvHoy;
    ArrayList<Ejercicio> listaArrayEjercicios;
    DbEjercicios dbEjercicios;
    Persona persona;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvHoy = findViewById(R.id.lvHoy);
        //btnCrear = findViewById(R.id.btnCrear);
        etPeso = findViewById(R.id.etPeso);
        etEdad = findViewById(R.id.etEdad);
        etPesoDeseado = findViewById(R.id.etPesoDeseado);
        etAltura = findViewById(R.id.etAltura);

        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Crear un objeto Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);

        // Obtener el día de la semana como un número (1 = Domingo, 2 = Lunes, ..., 7 = Sábado)
        int numeroDiaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        // Obtener el día de la semana como texto (por ejemplo, "Lunes")
        String nombreDiaSemana = obtenerNombreDiaSemana(numeroDiaSemana);
        dbEjercicios = new DbEjercicios(MainActivity.this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = 0; // Establece un valor predeterminado si no hay extras
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        if (persona == null) {
            cargarValoresDesdeSharedPreferences();
        }


        DbPersona dbPersona = new DbPersona(MainActivity.this);
        persona = dbPersona.verPersonas(id);

        if(persona != null){
            etPeso.setText(persona.getPeso());
            etEdad.setText(persona.getEdad());
            etPesoDeseado.setText(persona.getPesoDeseado());
            etAltura.setText(persona.getAltura());
            etPeso.setInputType(InputType.TYPE_NULL);
            etEdad.setInputType(InputType.TYPE_NULL);
            etPesoDeseado.setInputType(InputType.TYPE_NULL);
            etAltura.setInputType(InputType.TYPE_NULL);
        }
        // Configura el RecyclerView inicialmente con el día seleccionado por defecto
        lvHoy.setLayoutManager(new LinearLayoutManager(this));
        String selectedDay = nombreDiaSemana;
        cargarEjerciciosPorDia(nombreDiaSemana);
        /*btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null) {
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });*/


    }
    private void cargarEjerciciosPorDia(String diaSeleccionado) {
        listaArrayEjercicios = dbEjercicios.mostrarEjerciciosPorDia(diaSeleccionado);
        ListaEjerciciosAdapter adapter = new ListaEjerciciosAdapter(listaArrayEjercicios);
        lvHoy.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.menu_e1, menu );
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        boolean toret = false;
        int itemId = menuItem.getItemId();

        if (itemId == R.id.itemNR) {
            this.goNR();
            toret=true;
        } else if (itemId == R.id.itemNE) {
            this.goNE();
            toret=true;
        }else if(itemId == R.id.itemNP){
            this.goNP();
            toret=true;
        }

        return toret;
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Guardar valores en SharedPreferences al pausar la actividad
        guardarValoresEnSharedPreferences();
    }

    private void guardarValoresEnSharedPreferences() {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("peso", etPeso.getText().toString());
        editor.putString("edad", etEdad.getText().toString());
        editor.putString("pesoDeseado", etPesoDeseado.getText().toString());
        editor.putString("altura", etAltura.getText().toString());
        editor.commit();
    }

    private void cargarValoresDesdeSharedPreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        etPeso.setText(sharedPreferences.getString("peso", ""));
        etEdad.setText(sharedPreferences.getString("edad", ""));
        etPesoDeseado.setText(sharedPreferences.getString("pesoDeseado", ""));
        etAltura.setText(sharedPreferences.getString("altura", ""));
    }
    private String obtenerNombreDiaSemana(int numeroDiaSemana) {
        String[] nombresDias = {"Domingo","Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        // Asegurarse de que el número del día de la semana esté en el rango correcto
        if (numeroDiaSemana >= 1 && numeroDiaSemana <= 7) {
            return nombresDias[numeroDiaSemana - 1];
        } else {
            return "Día no válido";
        }
    }

    private void goNP(){
        Intent intent = new Intent(this, PersonasActivity.class);
        startActivity(intent);
    }
    private void goNR() {
        // Crear un Intent para iniciar la nueva actividad
        Intent intent = new Intent(this, RutinaActivity.class);
        startActivity(intent);
    }
    private void goNE() {
        // Crear un Intent para iniciar la nueva actividad
        Intent intent = new Intent(this, EntrenoActivity.class);
        startActivity(intent);
    }


}