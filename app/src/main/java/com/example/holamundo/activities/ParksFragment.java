package com.example.holamundo.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holamundo.R;
import com.example.holamundo.adapters.ParkAdapter;
import com.example.holamundo.model.Park;

import java.util.ArrayList;
import java.util.List;


public class ParksFragment extends Fragment implements ParkAdapter.ParkAdapterOnClickHandler {

    private RecyclerView recyclerView;
    private ParkAdapter adapter;
    private NavController navController;

    public ParksFragment() {
        // Se requiere un constructor vacío.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Aquí enlazamos ParksFragment con la vista fragment_parks
        return inflater.inflate(R.layout.fragment_parks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        recyclerView = view.findViewById(R.id.parks);

        SetUpRecyclerView();
    }

    @Override
    public void onClick(Park park) {
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("parkName", park.getName());
        fragment.setArguments(bundle);

        navController.navigate(R.id.action_parksFragment_to_parksDetailFragment, bundle);
    }

    private void SetUpRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new ParkAdapter(getContext(),this, getListNationalParks());
        recyclerView.setAdapter(adapter);
    }

    // Acá creamos nuestra lista de parques nacionales
    private List<Park> getListNationalParks() {
        List<Park> nationalParks = new ArrayList<Park>();
        // Acá usamos la clase R que accede a los recursos para obtener el id asignado por Android
        // del recurso, en este caso de una imagen que está dentro de las carpetas drawable
        nationalParks.add(new Park("Torres del Paine", R.drawable.torres_del_paine));
        nationalParks.add(new Park("Rapa Nui", R.drawable.rapa_nui));
        nationalParks.add(new Park("Fray Jorge", R.drawable.parque_fray_jorge));
        nationalParks.add(new Park("Rio Clarillo", R.drawable.parque_rio_clarillo));

        return nationalParks;
    }

}