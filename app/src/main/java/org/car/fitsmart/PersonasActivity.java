package org.car.fitsmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.adaptadores.ListaEjerciciosAdapter;
import org.car.fitsmart.adaptadores.ListaPersonasAdapter;
import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.DbHelper;
import org.car.fitsmart.db.DbPersona;
import org.car.fitsmart.db.Persona;

import java.util.ArrayList;

public class PersonasActivity extends AppCompatActivity {

    DbHelper dbHelper = new DbHelper(this);
    RecyclerView listaPersonas;
    ArrayList<Persona> listaArrayPersonas;

    FloatingActionButton perRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);
        perRegistro = findViewById(R.id.perRegistro);


        perRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaPersona();
            }
        });

        listaPersonas = findViewById(R.id.listaPersonas);
        listaPersonas.setLayoutManager(new LinearLayoutManager(this));

        DbPersona dbPersona = new DbPersona(PersonasActivity.this);

        listaArrayPersonas = new ArrayList<>();

        ListaPersonasAdapter adapter = new ListaPersonasAdapter(dbPersona.mostrarPersna());
        listaPersonas.setAdapter(adapter);
    }



    private void nuevaPersona(){
        Intent intent = new Intent(this, nuevaPersona.class);
        startActivity(intent);
    }
}
