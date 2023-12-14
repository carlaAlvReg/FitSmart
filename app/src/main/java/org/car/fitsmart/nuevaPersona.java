package org.car.fitsmart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.car.fitsmart.db.DbPersona;

public class nuevaPersona extends AppCompatActivity {
    EditText txtNombre, txtPeso, txtAltura, txtPesoDeseado, txtSexo, txtEdad;
    Button btnGuarda;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_persona);

        txtNombre = findViewById(R.id.txtNombre);
        txtAltura = findViewById(R.id.txtAltura);
        txtPeso = findViewById(R.id.txtPeso);
        txtSexo = findViewById(R.id.txtSexo);
        txtPesoDeseado = findViewById(R.id.txtPesoDeseado);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuarda = findViewById(R.id.btnGuardaPersona);

       btnGuarda.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DbPersona dbPersona = new DbPersona(nuevaPersona.this);
               long id = dbPersona.insertarPersona(txtNombre.getText().toString(), txtAltura.getText().toString(), txtPeso.getText().toString(), txtSexo.getText().toString(),txtPesoDeseado.getText().toString(), txtEdad.getText().toString());
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
        txtSexo.setText("");
        txtPesoDeseado.setText("");
        txtEdad.setText("");


    }
}
