package org.car.fitsmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.car.fitsmart.adaptadores.ListaEjerciciosAdapter;
import org.car.fitsmart.adaptadores.SpinnerDiaAdapter;
import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.DbHelper;
import org.car.fitsmart.db.Ejercicio;

import java.util.ArrayList;

public class EntrenoActivity extends AppCompatActivity {

    RecyclerView lvListaNE;
    DbHelper dbHelper = new DbHelper(this);
    ArrayList<Ejercicio> listaArrayEjercicios;

    DbEjercicios dbEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreno);
        lvListaNE = findViewById(R.id.lvListaNE);

        Spinner spinner = findViewById(R.id.SpinnerDia);

        // Obtén la lista de días desde los recursos
        String[] daysArray = getResources().getStringArray(R.array.days_array);

        // Crea un adaptador personalizado para el Spinner
        SpinnerDiaAdapter adapter = new SpinnerDiaAdapter(this, daysArray);

        // Asigna el adaptador al Spinner
        spinner.setAdapter(adapter);

        dbEjercicios = new DbEjercicios(EntrenoActivity.this);
        // Maneja la selección de elementos
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Cuando se selecciona un elemento en el Spinner, se ejecuta este código.
                String selectedDay = daysArray[position];
                cargarEjerciciosPorDia(selectedDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Puedes manejar la situación cuando no se ha seleccionado nada, si es necesario.
            }
        });

        // Configura el RecyclerView inicialmente con el día seleccionado por defecto
        lvListaNE.setLayoutManager(new LinearLayoutManager(this));
        String selectedDay = daysArray[spinner.getSelectedItemPosition()];
        cargarEjerciciosPorDia(selectedDay);
    }

    private void cargarEjerciciosPorDia(String diaSeleccionado) {
        listaArrayEjercicios = dbEjercicios.mostrarEjerciciosPorDia(diaSeleccionado);
        ListaEjerciciosAdapter adapter = new ListaEjerciciosAdapter(listaArrayEjercicios);
        lvListaNE.setAdapter(adapter);
    }

}