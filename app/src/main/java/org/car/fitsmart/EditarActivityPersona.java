package org.car.fitsmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.DbPersona;
import org.car.fitsmart.db.Persona;

public class EditarActivityPersona extends AppCompatActivity {

    EditText txtNombre, txtPeso, txtAltura, txtPesoDeseado, txtSexo, txtEdad;
    Button btnGuarda;
    boolean correcto = false;

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

        DbPersona dbPersona = new DbPersona(EditarActivityPersona.this);
        persona = dbPersona.verPersonas(id);

        if(persona != null){
            txtNombre.setText(persona.getNombre());
            txtAltura.setText(persona.getAltura());
            txtPeso.setText(persona.getPeso());
            txtSexo.setText(persona.getSexo());
            txtPesoDeseado.setText(persona.getPesoDeseado());
            txtEdad.setText(persona.getEdad());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtAltura.getText().toString().equals("") && !txtPeso.getText().toString().equals("") && !txtSexo.getText().toString().equals("") && !txtPesoDeseado.getText().toString().equals("") && !txtEdad.getText().toString().equals("")){
                    correcto = dbPersona.editarPersona(id, txtNombre.getText().toString(),txtAltura.getText().toString(), txtPeso.getText().toString(), txtSexo.getText().toString(),txtPesoDeseado.getText().toString(), txtEdad.getText().toString());
                    if(correcto){
                        Toast.makeText(EditarActivityPersona.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivityPersona.this, "ERROR AL MODIFICAR REGISTRO ", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(EditarActivityPersona.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, ver_persona_activity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
