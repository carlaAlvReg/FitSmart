package org.car.fitsmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.car.fitsmart.db.DbHelper;

public class MainActivity extends AppCompatActivity {
    Button btnCrear;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*btnCrear = findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
            }
        });*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.menu_e1, menu );
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        boolean toret = false;
        int itemId = menuItem.getItemId();

        if (itemId == R.id.itemNR) {
            this.goNR();
            toret=true;
        } else if (itemId == R.id.itemNE) {
            this.goNE();
            toret=true;
        }else if(itemId == R.id.itemNP){
            this.goNP();
            toret=true;
        }

        return toret;
    }

    private void goNP(){
        Intent intent = new Intent(this, PersonasActivity.class);
        startActivity(intent);
    }
    private void goNR() {
        // Crear un Intent para iniciar la nueva actividad
        Intent intent = new Intent(this, RutinaActivity.class);
        startActivity(intent);
    }
    private void goNE() {
        // Crear un Intent para iniciar la nueva actividad
        Intent intent = new Intent(this, EntrenoActivity.class);
        startActivity(intent);
    }


}