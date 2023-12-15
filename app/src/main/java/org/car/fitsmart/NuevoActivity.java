package org.car.fitsmart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.db.DbEjercicios;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtNivelDificultad, txtGrupoMuscular, txtNumSeries, txtNumRepes, txtDia;
    Button btnGuarda;
    FloatingActionButton btnNivelDificultad;
    RadioGroup radioGroup;
    TextView textNivelDificultad;
    String opcionSeleccionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        //txtNivelDificultad = findViewById(R.id.txtNivelDificultad);
        txtGrupoMuscular = findViewById(R.id.txtGrupoMuscular);
        txtNumSeries = findViewById(R.id.txtNumSeries);
        txtNumRepes = findViewById(R.id.txtNumRepes);
        txtDia = findViewById(R.id.txtDia);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnNivelDificultad = findViewById(R.id.btnNivelDificultad);
        textNivelDificultad = findViewById(R.id.textNivelDificultad);
        radioGroup = new RadioGroup(this);

        btnNivelDificultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarModal();
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener la opción seleccionada del RadioGroup
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                String nivelDificultad = "";

                if (radioButtonId != -1) {
                    RadioButton radioButton = findViewById(radioButtonId);
                    // Verificar que el objeto RadioButton no sea null antes de obtener el texto
                    if (radioButton != null) {
                        nivelDificultad = radioButton.getText().toString();
                    }
                }
                DbEjercicios dbEjercicios = new DbEjercicios(NuevoActivity.this);
                long id = dbEjercicios.insertaEjercicio(txtNombre.getText().toString(), opcionSeleccionada, txtGrupoMuscular.getText().toString(), txtNumSeries.getText().toString(),txtNumRepes.getText().toString(), txtDia.getText().toString());
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
        //txtNivelDificultad.setText("");
        txtGrupoMuscular.setText("");
        txtNumSeries.setText("");
        txtNumRepes.setText("");
        txtDia.setText("");


    }

    // Método mostrarModal
    // Método mostrarModal
    private void mostrarModal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        // Crear un RadioGroup y agregar opciones
        final RadioGroup radioGroup = new RadioGroup(this);
        final String[] opciones = {"Principiante", "Intermedio", "Avanzado"};

        for (String opcion : opciones) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(opcion);
            radioGroup.addView(radioButton);
        }

        builder.setView(radioGroup);

        // Configurar el botón positivo (o "Aceptar")
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener la opción seleccionada al hacer clic en "Aceptar"
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                if (radioButtonId != -1) {
                    RadioButton radioButton = radioGroup.findViewById(radioButtonId);
                    if (radioButton != null) {
                        opcionSeleccionada = radioButton.getText().toString();

                        // Mostrar la opción seleccionada en el TextView
                        textNivelDificultad.setText(opcionSeleccionada);
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