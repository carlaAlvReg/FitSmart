package org.car.fitsmart.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.car.fitsmart.R;
import org.car.fitsmart.VerActivity;
import org.car.fitsmart.db.Persona;

import java.util.ArrayList;

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonaViewHolder>{
    ArrayList<Persona> listaPersona;



    public ListaPersonasAdapter(ArrayList<Persona> listaPersona){
        this.listaPersona = listaPersona;
    }


    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lvlista_np, null, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPersonasAdapter.PersonaViewHolder holder, int position) {
        holder.viewNombre.setText(listaPersona.get(position).getNombre());
        holder.viewAltura.setText(listaPersona.get(position).getAltura());
        holder.viewPeso.setText(listaPersona.get(position).getPeso());
        holder.viewSexo.setText(listaPersona.get(position).getSexo());
        holder.viewPD.setText(listaPersona.get(position).getPesoDeseado());
        holder.viewEdad.setText(listaPersona.get(position).getEdad());
    }

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public class PersonaViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewAltura, viewPeso, viewSexo, viewPD, viewEdad;
        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewAltura = itemView.findViewById(R.id.viewAltura);
            viewPeso = itemView.findViewById(R.id.viewPeso);
            viewSexo = itemView.findViewById(R.id.viewSexo);
            viewPD = itemView.findViewById(R.id.viewPD);
            viewEdad = itemView.findViewById(R.id.viewEdad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaPersona.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }


}
