package org.car.fitsmart.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.car.fitsmart.R;
import org.car.fitsmart.db.Ejercicio;

import java.util.ArrayList;

public class ListaEjerciciosAdapter extends RecyclerView.Adapter<ListaEjerciciosAdapter.EjercicioViewHolder>{

    ArrayList<Ejercicio> listaEjercicio;

    public ListaEjerciciosAdapter(ArrayList<Ejercicio> listaEjercicio){
        this.listaEjercicio = listaEjercicio;
    }
    @NonNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lvlista_nr, null, false);
        return new EjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioViewHolder holder, int position) {
        holder.viewNombre.setText(listaEjercicio.get(position).getNombre());
        holder.viewND.setText(listaEjercicio.get(position).getNivel_dificultad());
        holder.viewGM.setText(listaEjercicio.get(position).getGrupo_muscular());
    }

    @Override
    public int getItemCount() {
       return listaEjercicio.size();
    }

    public class EjercicioViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewND, viewGM;
        public EjercicioViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewND = itemView.findViewById(R.id.viewND);
            viewGM = itemView.findViewById(R.id.viewGM);

        }
    }
}
