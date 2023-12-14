package org.car.fitsmart.adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.car.fitsmart.R;

public class SpinnerDiaAdapter extends ArrayAdapter<String> {
    public SpinnerDiaAdapter(Context context, String[] days) {
        super(context, android.R.layout.simple_spinner_item, days);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = view.findViewById(android.R.id.text1);
        // Personaliza la apariencia del elemento seleccionado si es necesario
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView textView = view.findViewById(android.R.id.text1);
        // Personaliza la apariencia de los elementos en el dropdown si es necesario
        return view;
    }
}
