package org.car.fitsmart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.car.fitsmart.db.DBManager;

/*public class EditorActivity extends Activity {

    // Referencias de los campos de texto y botones
    EditText etNombre, etDescripcion;
    Button btGuarda;
    private SQLiteDatabase myDatabase;


    // Método onCreate
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editoractivity);
        myDatabase = new DBManager(this).getReadableDatabase();
        // Obtener las referencias de los campos de texto y botones
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        btGuarda = findViewById(R.id.btGuarda);

        // Agregar un OnClickListener al botón btGuarda
        btGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método guardaDatos para guardar la información en la base de datos
                guardaDatos();
            }
        });
    }

    // Método para guardar la información en la base de datos
    private void guardaDatos() {
        // Obtener los valores ingresados por el usuario
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        // Validar la información antes de insertarla
        if (!nombre.isEmpty() && !descripcion.isEmpty()) {
            // Llamar al método insertaDatos para insertar la información en la base de datos
            insertaDatos(nombre, descripcion);
        } else {
            // Mostrar un mensaje de error si los campos están vacíos
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para insertar la información en la base de datos
    private void insertaDatos(String nombre, String descripcion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.COLUMNA_NOMBRE, nombre);
        contentValues.put(DBManager.COLUMNA_DESCRIPCION, descripcion);

        myDatabase.insert(DBManager.TABLE_NAME, null, contentValues);
        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

    }
/*}*/
