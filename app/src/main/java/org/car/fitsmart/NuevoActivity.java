package org.car.fitsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.car.fitsmart.db.DbEjercicios;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtNivelDificultad, txtGrupoMuscular;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtNivelDificultad = findViewById(R.id.txtNivelDificultad);
        txtGrupoMuscular = findViewById(R.id.txtGrupoMuscular);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEjercicios dbEjercicios = new DbEjercicios(NuevoActivity.this);
                long id = dbEjercicios.insertaEjercicio(txtNombre.getText().toString(), txtNivelDificultad.getText().toString(), txtGrupoMuscular.getText().toString());
                System.out.println("El id es"+id);
                if(id > 0){
                    Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(NuevoActivity.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void limpiar(){
        txtNombre.setText("");
        txtNivelDificultad.setText("");
        txtGrupoMuscular.setText("");

    }
}