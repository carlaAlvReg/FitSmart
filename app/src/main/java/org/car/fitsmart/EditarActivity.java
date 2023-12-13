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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.Ejercicio;

public class EditarActivity extends AppCompatActivity {
    EditText txtNombre, txtNivelDificultad, txtGrupoMuscular, txtNumSeries, txtNumRepes, txtDia;
    Button btnGuarda;
    FloatingActionButton fabEdit, fabDelete;

    boolean correcto = false;

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
        fabEdit.setVisibility(View.INVISIBLE);
        fabDelete = findViewById(R.id.fabDelete);
        fabDelete.setVisibility(View.INVISIBLE);

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

        DbEjercicios dbEjercicios = new DbEjercicios(EditarActivity.this);
        ejercicio = dbEjercicios.verEjercicios(id);

        if(ejercicio != null){
            txtNombre.setText(ejercicio.getNombre());
            txtNivelDificultad.setText(ejercicio.getNivel_dificultad());
            txtGrupoMuscular.setText(ejercicio.getGrupo_muscular());
            txtNumSeries.setText(ejercicio.getNum_series());
            txtNumRepes.setText(ejercicio.getNum_repes());
            txtDia.setText(ejercicio.getDia());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtNivelDificultad.getText().toString().equals("") && !txtGrupoMuscular.getText().toString().equals("") && !txtNumSeries.getText().toString().equals("") && !txtNumRepes.getText().toString().equals("") && !txtDia.getText().toString().equals("")){
                    correcto = dbEjercicios.editarEjercicio(id, txtNombre.getText().toString(),txtNivelDificultad.getText().toString(), txtGrupoMuscular.getText().toString(), txtNumSeries.getText().toString(),txtNumRepes.getText().toString(), txtDia.getText().toString());
                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO ", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
