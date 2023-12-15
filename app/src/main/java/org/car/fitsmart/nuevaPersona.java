package org.car.fitsmart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.db.DbPersona;

public class nuevaPersona extends AppCompatActivity {
    EditText txtNombre, txtPeso, txtAltura, txtPesoDeseado, txtEdad;

    FloatingActionButton btnSexo;
    RadioGroup radioGroupSexo;
    TextView textSexo;
    String opcionSexo = "";
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_persona);

        btnSexo =  findViewById(R.id.btnSexo);

        txtNombre = findViewById(R.id.txtNombre);
        txtAltura = findViewById(R.id.txtAltura);
        txtPeso = findViewById(R.id.txtPeso);
        textSexo = findViewById(R.id.txtSexo);
        txtPesoDeseado = findViewById(R.id.txtPesoDeseado);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuarda = findViewById(R.id.btnGuardaPersona);

        btnSexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarModalSexo();
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbPersona dbPersona = new DbPersona(nuevaPersona.this);
                long id = dbPersona.insertarPersona(txtNombre.getText().toString(), txtAltura.getText().toString(), txtPeso.getText().toString(), opcionSexo,txtPesoDeseado.getText().toString(), txtEdad.getText().toString());
                System.out.println("El id es"+id);
                if(id > 0){
                    Toast.makeText(nuevaPersona.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(nuevaPersona.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
        textSexo.setText("");
        txtPesoDeseado.setText("");
        txtEdad.setText("");


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
