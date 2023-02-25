package com.example.holamundo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.holamundo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // llama a la superclase onCreate para completar la creación del activity como
        // parte de la jerarquía de vistas
        super.onCreate(savedInstanceState);

        // establece el diseño de la interfaz de usuario para este Activity
        // el archivo de diseño se define en el archivo res/layout/main_activity.xml del proyecto
        setContentView(R.layout.activity_main);

    }

}