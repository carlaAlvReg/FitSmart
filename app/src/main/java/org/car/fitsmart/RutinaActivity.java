package org.car.fitsmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.adaptadores.ListaEjerciciosAdapter;
import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.DbHelper;
import org.car.fitsmart.db.Ejercicio;

import java.util.ArrayList;

public class RutinaActivity extends AppCompatActivity {

    FloatingActionButton fabRegistro;
    DbHelper dbHelper = new DbHelper(this);
    RecyclerView listaEjercicios;
    ArrayList<Ejercicio> listaArrayEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina);

        fabRegistro = findViewById(R.id.fabRegistro);


        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        listaEjercicios = findViewById(R.id.listaEjercicios);
        listaEjercicios.setLayoutManager(new LinearLayoutManager(this));

        DbEjercicios dbEjercicios = new DbEjercicios(RutinaActivity.this);

        listaArrayEjercicios = new ArrayList<>();

        ListaEjerciciosAdapter adapter = new ListaEjerciciosAdapter(dbEjercicios.mostrarEjercicios());
        listaEjercicios.setAdapter(adapter);
    }
    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}