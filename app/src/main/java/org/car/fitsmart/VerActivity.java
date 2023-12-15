package org.car.fitsmart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.Ejercicio;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtNivelDificultad, txtGrupoMuscular, txtNumSeries, txtNumRepes, txtDia;
    Button btnGuarda;
    FloatingActionButton fabEdit, fabDelete, fabNivelDificultad, fabGrupoMuscular, fabDia;

    Ejercicio ejercicio;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtNivelDificultad = findViewById(R.id.txtNivelDificultad);
        txtGrupoMuscular = findViewById(R.id.txtGrupoMuscular);
        txtNumSeries = findViewById(R.id.txtNumSeries);
        txtNumRepes = findViewById(R.id.txtNumRepes);
        txtDia = findViewById(R.id.txtDia);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEdit = findViewById(R.id.fabEdit);
        fabDelete = findViewById(R.id.fabDelete);
        fabNivelDificultad = findViewById(R.id.fabNivelDificultad);
        fabGrupoMuscular = findViewById(R.id.fabGrupoMuscular);
        fabDia = findViewById(R.id.fabDia);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbEjercicios dbEjercicios = new DbEjercicios(VerActivity.this);
        ejercicio = dbEjercicios.verEjercicios(id);

        if(ejercicio != null){
            txtNombre.setText(ejercicio.getNombre());
            txtNivelDificultad.setText(ejercicio.getNivel_dificultad());
            txtGrupoMuscular.setText(ejercicio.getGrupo_muscular());
            txtNumSeries.setText(ejercicio.getNum_series());
            txtNumRepes.setText(ejercicio.getNum_repes());
            txtDia.setText(ejercicio.getDia());
            btnGuarda.setVisibility(View.INVISIBLE);
            fabNivelDificultad.setVisibility(View.INVISIBLE);
            fabGrupoMuscular.setVisibility(View.INVISIBLE);
            fabDia.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtNivelDificultad.setInputType(InputType.TYPE_NULL);
            txtGrupoMuscular.setInputType(InputType.TYPE_NULL);
            txtNumSeries.setInputType(InputType.TYPE_NULL);
            txtNumRepes.setInputType(InputType.TYPE_NULL);
            txtDia.setInputType(InputType.TYPE_NULL);
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Seguro que quieres eliminar este ejercicio?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dbEjercicios.eliminarEjercicio(id)){
                            lista();
                        }

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });

    }

   private void lista(){
        Intent intent = new Intent(this, RutinaActivity.class);
        startActivity(intent);
    }


}