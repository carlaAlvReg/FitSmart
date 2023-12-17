package org.car.fitsmart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.db.DbEjercicios;
import org.car.fitsmart.db.DbPersona;
import org.car.fitsmart.db.Persona;

public class EditarActivityPersona extends AppCompatActivity {

    EditText txtNombre, txtPeso, txtAltura, txtPesoDeseado, txtEdad;
    Button btnGuarda;
    boolean correcto = false;
    Switch switchPersona;

    FloatingActionButton btnSexo;
    RadioGroup radioGroupSexo;
    TextView textSexo;
    String opcionSexo = "";

    FloatingActionButton fabEdit, fabDelete;

    Persona persona;

    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_persona);
        btnSexo =  findViewById(R.id.btnSexo);


        txtNombre = findViewById(R.id.txtNombre);
        txtAltura = findViewById(R.id.txtAltura);
        txtPeso = findViewById(R.id.txtPeso);
        textSexo = findViewById(R.id.txtSexo);
        txtPesoDeseado = findViewById(R.id.txtPesoDeseado);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEdit = findViewById(R.id.fabEdit);
        fabEdit.setVisibility(View.INVISIBLE);
        fabDelete = findViewById(R.id.fabDelete);
        fabDelete.setVisibility(View.INVISIBLE);
        switchPersona = findViewById(R.id.switchPersona);
        switchPersona.setVisibility(View.INVISIBLE);

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

        btnSexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarModalSexo();
            }
        });

        DbPersona dbPersona = new DbPersona(EditarActivityPersona.this);
        persona = dbPersona.verPersonas(id);

        if(persona != null){
            txtNombre.setText(persona.getNombre());
            txtAltura.setText(persona.getAltura());
            txtPeso.setText(persona.getPeso());
            textSexo.setText(persona.getSexo());
            txtPesoDeseado.setText(persona.getPesoDeseado());
            txtEdad.setText(persona.getEdad());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtAltura.getText().toString().equals("") && !txtPeso.getText().toString().equals("") && !opcionSexo.equals("") && !txtPesoDeseado.getText().toString().equals("") && !txtEdad.getText().toString().equals("")){
                    correcto = dbPersona.editarPersona(id, txtNombre.getText().toString(),txtAltura.getText().toString(), txtPeso.getText().toString(),opcionSexo,txtPesoDeseado.getText().toString(), txtEdad.getText().toString());
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

    public void mostrarModalSexo() {
        radioGroupSexo = new RadioGroup(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        // Crear un RadioGroup y agregar opciones
        final String[] opciones = {"Masculino", "Femenino", "Otro"};

        // Limpiar selecciones previas
        radioGroupSexo.clearCheck();
        // Limpiar las vistas existentes en el RadioGroup antes de agregar nuevas opciones
        radioGroupSexo.removeAllViews();
        for (String opcion : opciones) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(opcion);
            radioGroupSexo.addView(radioButton);
        }

        builder.setView(radioGroupSexo);

        // Configurar el botón positivo (o "Aceptar")
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener la opción seleccionada al hacer clic en "Aceptar"
                int radioButtonId = radioGroupSexo.getCheckedRadioButtonId();
                if (radioButtonId != -1) {
                    RadioButton radioButton = radioGroupSexo.findViewById(radioButtonId);
                    if (radioButton != null) {
                        opcionSexo = radioButton.getText().toString();

                        // Mostrar la opción seleccionada en el TextView
                        textSexo.setText(opcionSexo);
                    }
                }
                dialog.dismiss();
            }
        });

        // Configurar el botón negativo (o "Cancelar")
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Mostrar el cuadro de diálogo
        builder.create().show();
    }
}
