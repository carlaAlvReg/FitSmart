package org.car.fitsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EntrenoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreno);

        Spinner spinner = findViewById(R.id.SpinnerDia);

        // Obtén la lista de días desde los recursos
        String[] daysArray = getResources().getStringArray(R.array.days_array);

        // Crea un adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, daysArray);

        // Especifica el diseño del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asigna el adaptador al Spinner
        spinner.setAdapter(adapter);

        // Maneja la selección de elementos
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Maneja la selección del día
                String selectedDay = daysArray[position];
                // Puedes realizar acciones con el día seleccionado aquí
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Maneja la situación cuando no se ha seleccionado nada
            }
        });
    }

}