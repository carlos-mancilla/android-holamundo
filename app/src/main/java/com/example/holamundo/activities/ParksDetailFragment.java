package com.example.holamundo.activities;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.holamundo.R;

public class ParksDetailFragment extends Fragment {

    public static String parkName;

    public ParksDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Aquí enlazamos ParksDetailFragment con la vista fragment_parks_detail
        return inflater.inflate(R.layout.fragment_parks_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            parkName = bundle.getString("parkName");
        } else {
            // Por defecto se agrega este nombre para que la vista muestre un parque por defecto.
            // El (PD) del nombre indica que fue generado por defecto y no por un valor recibido
            parkName = "Torres del Paine (PD)";
        }

        // se inicializa el miembro TextView para que podamos manipularlo más tarde
        TextView nameParkText = (TextView) getView().findViewById(R.id.nameParkText);

        // se agrega el texto correspondiente
        nameParkText.setText(parkName);

        // se inicializa el miembro TextView para que podamos manipularlo más tarde
        TextView descriptionParkText = (TextView) getView().findViewById(R.id.descriptionParkText);

        // se agrega habilita scroll bar
        descriptionParkText.setMovementMethod(new ScrollingMovementMethod());

    }

}
