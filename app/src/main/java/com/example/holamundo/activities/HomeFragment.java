package com.example.holamundo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.holamundo.R;

public class HomeFragment extends Fragment {
    // En estas constantes vamos a guardar el key de un estado transitorio
    private static final String HOLA_STATE_KEY = "HOLA_KEY";
    private static final String TEXT_VIEW_KEY = "TEXT_KEY";
    private static final String TAG = "HomeFragment";

    private NavController navController;

    TextView textView;

    // algún estado transitorio para la instancia del Activity
    String holaState;
    String textViewText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // acá obtenemos el valor del string guardado en el archivo strings.xml para el textView
        textViewText = getResources().getString(R.string.textView_text);

        // vamos a dar un valor inicial por defecto a holaState, debe ser String.
        holaState = "0";

        // recuperando el estado de la instancia
        if (savedInstanceState != null) {
            // sobreescribimos el valor de holaState y textViewText por uno guardado en memoria
            holaState = savedInstanceState.getString(HOLA_STATE_KEY);
            textViewText = savedInstanceState.getString(TEXT_VIEW_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Aqui enlazamos HomeFragment con la vista fragment_home
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        // se inicializa el miembro TextView para que podamos manipularlo más tarde
        textView = (TextView) getView().findViewById(R.id.textView);

        // se agrega el texto correspondiente
        textView.setText(textViewText);

        // se inicializa el miembro Button para que podamos manipularlo más tarde
        Button myButton = (Button) getView().findViewById(R.id.my_button);

        // acá vamos a hacer una accion con el botón. En nuestro caso vamos a cambiar nuestro saludo
        // en el textView agregando el numero de veces que hemos hecho clic en esta ejecución.
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // obtenemos el valor de la variable holaState y lo convertimos a int para poder utilizarlo.
                int holaStateInt = Integer.parseInt(holaState);
                // una vez obtenido el valor lo incrementamos en 1 con el operador incremento.
                holaStateInt++;
                // creamos un nuevo String con el texto definido en strings.xml y luego concatenamos
                // el valor entero de holaStateInt y se lo asignamos nuevamente a textViewText.
                // Poner especial atención en el uso del método split de la clase String.
                textViewText = textViewText.split(" - ")[0]+ " - " + holaStateInt;
                // luego guardamos en nuevo valor en la variable global holaState, es necesario
                // convertirlo en String para guardarlo.
                holaState = String.valueOf(holaStateInt);
                // se actualiza el valor del textView para poder ver el cambio en la UI
                textView.setText(textViewText);
            }
        });

        // se inicializa el miembro Button goParksButton para que podamos manipularlo más tarde
        Button goParksButton = (Button) getView().findViewById(R.id.go_parks_button);

        // acá vamos a hacer una accion con el botón goParksButton.
        // En nuestro caso vamos a navegar al listado de parques nacionales.
        goParksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // acá vamos a navegar a la lista de parques
                navController.navigate(R.id.action_homeFragment_to_parksFragment);
            }
        });
    }

    // Se invoca cuando el Fragment puede destruirse temporalmente, guarde el estado
    // de la instancia aquí.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(HOLA_STATE_KEY, holaState);
        // vamos a agregar esta línea para saber cuando se guarda el estado
        Log.d(TAG,"onSaveInstanceState:: holaState: " + holaState);
        outState.putString(TEXT_VIEW_KEY, textViewText);

        // llama a la superclase para guardar cualquier jerarquía de vista
        super.onSaveInstanceState(outState);
    }
}
