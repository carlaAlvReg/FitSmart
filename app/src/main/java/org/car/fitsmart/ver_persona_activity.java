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
import org.car.fitsmart.db.DbPersona;
import org.car.fitsmart.db.Ejercicio;
import org.car.fitsmart.db.Persona;

public class ver_persona_activity extends AppCompatActivity {

    EditText txtNombre, txtPeso, txtAltura, txtPesoDeseado, txtSexo, txtEdad;
    Button btnGuarda;

    FloatingActionButton fabEdit, fabDelete;
    Persona persona;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_persona);

        txtNombre = findViewById(R.id.txtNombre);
        txtAltura = findViewById(R.id.txtAltura);
        txtPeso = findViewById(R.id.txtPeso);
        txtSexo = findViewById(R.id.txtSexo);
        txtPesoDeseado = findViewById(R.id.txtPesoDeseado);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuarda = findViewById(R.id.btnGuarda);

        fabEdit = findViewById(R.id.fabEdit);
        fabDelete = findViewById(R.id.fabDelete);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbPersona dbPersona = new DbPersona(ver_persona_activity.this);
        persona = dbPersona.verPersonas(id);

        if(persona != null){
            txtNombre.setText(persona.getNombre());
            txtAltura.setText(persona.getAltura());
            txtPeso.setText(persona.getPeso());
            txtSexo.setText(persona.getSexo());
            txtPesoDeseado.setText(persona.getPesoDeseado());
            txtEdad.setText(persona.getEdad());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtAltura.setInputType(InputType.TYPE_NULL);
            txtPeso.setInputType(InputType.TYPE_NULL);
            txtSexo.setInputType(InputType.TYPE_NULL);
            txtPesoDeseado.setInputType(InputType.TYPE_NULL);
            txtEdad.setInputType(InputType.TYPE_NULL);
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ver_persona_activity.this, EditarActivityPersona.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ver_persona_activity.this);
                builder.setMessage("¿Seguro que quieres eliminar este ejercicio?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dbPersona.eliminarPersona(id)){
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
        Intent intent = new Intent(this, PersonasActivity.class);
        startActivity(intent);
    }
}