package org.car.fitsmart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.car.fitsmart.db.DbEjercicios;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtNumSeries, txtNumRepes;
    Button btnGuarda;
    FloatingActionButton btnNivelDificultad, btnDia, btnGrupoMuscular;
    RadioGroup radioGroupNivelDificultad, radioGroupDia, radioGroupGrupoMuscular;
    TextView textNivelDificultad, textDia, textGrupoMuscular;
    String opcionNivelDificultad = "";
    String opcionDia = "";
    String opcionGrupoMuscular = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        //txtNivelDificultad = findViewById(R.id.txtNivelDificultad);
        //txtGrupoMuscular = findViewById(R.id.txtGrupoMuscular);
        txtNumSeries = findViewById(R.id.txtNumSeries);
        txtNumRepes = findViewById(R.id.txtNumRepes);
        //txtDia = findViewById(R.id.txtDia);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnNivelDificultad = findViewById(R.id.btnNivelDificultad);
        btnDia = findViewById(R.id.btnDia);
        btnGrupoMuscular = findViewById(R.id.btnGrupoMuscular);
        textNivelDificultad = findViewById(R.id.textNivelDificultad);
        textDia = findViewById(R.id.textDia);
        textGrupoMuscular = findViewById(R.id.textGrupoMuscular);




        btnNivelDificultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarModalNivelDificultad();
            }
        });

        btnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarModalDia();
            }
        });

        btnGrupoMuscular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarModalGrupoMuscular();
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*// Obtener la opción seleccionada del RadioGroup
                int radioButtonId = radioGroupNivelDificultad.getCheckedRadioButtonId();
                String nivelDificultad = "";

                if (radioButtonId != -1) {
                    RadioButton radioButton = findViewById(radioButtonId);
                    // Verificar que el objeto RadioButton no sea null antes de obtener el texto
                    if (radioButton != null) {
                        nivelDificultad = radioButton.getText().toString();
                    }
                }*/
                DbEjercicios dbEjercicios = new DbEjercicios(NuevoActivity.this);
                long id = dbEjercicios.insertaEjercicio(txtNombre.getText().toString(), opcionNivelDificultad, opcionGrupoMuscular, txtNumSeries.getText().toString(),txtNumRepes.getText().toString(), opcionDia);
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
        textNivelDificultad.setText("");
        textGrupoMuscular.setText("");
        txtNumSeries.setText("");
        txtNumRepes.setText("");
        textDia.setText("");

    }

    // Método mostrarModal
    // Método mostrarModal
    public void mostrarModalNivelDificultad() {
        radioGroupNivelDificultad = new RadioGroup(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        // Crear un RadioGroup y agregar opciones
        final String[] opciones = {"Principiante", "Intermedio", "Avanzado"};

        // Limpiar selecciones previas
        radioGroupNivelDificultad.clearCheck();
       // Limpiar las vistas existentes en el RadioGroup antes de agregar nuevas opciones
        radioGroupNivelDificultad.removeAllViews();
        for (String opcion : opciones) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(opcion);
            radioGroupNivelDificultad.addView(radioButton);
        }

        builder.setView(radioGroupNivelDificultad);

        // Configurar el botón positivo (o "Aceptar")
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener la opción seleccionada al hacer clic en "Aceptar"
                int radioButtonId = radioGroupNivelDificultad.getCheckedRadioButtonId();
                if (radioButtonId != -1) {
                    RadioButton radioButton = radioGroupNivelDificultad.findViewById(radioButtonId);
                    if (radioButton != null) {
                        opcionNivelDificultad = radioButton.getText().toString();

                        // Mostrar la opción seleccionada en el TextView
                        textNivelDificultad.setText(opcionNivelDificultad);
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
    private void mostrarModalDia() {
        radioGroupDia = new RadioGroup(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona un día de la semana");

        final String[] opciones = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};


        // Limpiar selecciones previas
        radioGroupDia.clearCheck();
        // Limpiar las vistas existentes en el RadioGroup antes de agregar nuevas opciones
        radioGroupDia.removeAllViews();

        for (String opcion : opciones) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(opcion);
            radioGroupDia.addView(radioButton);
        }
        builder.setView(radioGroupDia);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int radioButtonId = radioGroupDia.getCheckedRadioButtonId();
                if (radioButtonId != -1) {
                    RadioButton radioButton = radioGroupDia.findViewById(radioButtonId);
                    if (radioButton != null) {
                        opcionDia = radioButton.getText().toString();
                        textDia.setText(opcionDia);
                    }
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void mostrarModalGrupoMuscular() {
        radioGroupGrupoMuscular = new RadioGroup(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        // Crear un RadioGroup y agregar opciones
        final String[] opciones = {"Pierna", "Hombro", "Espalda", "Pecho", "Bíceps", "Tríceps", "Glúteo"};

        // Limpiar selecciones previas
        radioGroupGrupoMuscular.clearCheck();
        // Limpiar las vistas existentes en el RadioGroup antes de agregar nuevas opciones
        radioGroupGrupoMuscular.removeAllViews();
        for (String opcion : opciones) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(opcion);
            radioGroupGrupoMuscular.addView(radioButton);
        }

        builder.setView(radioGroupGrupoMuscular);

        // Configurar el botón positivo (o "Aceptar")
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener la opción seleccionada al hacer clic en "Aceptar"
                int radioButtonId = radioGroupGrupoMuscular.getCheckedRadioButtonId();
                if (radioButtonId != -1) {
                    RadioButton radioButton = radioGroupGrupoMuscular.findViewById(radioButtonId);
                    if (radioButton != null) {
                        opcionGrupoMuscular = radioButton.getText().toString();

                        // Mostrar la opción seleccionada en el TextView
                        textGrupoMuscular.setText(opcionGrupoMuscular);
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