package com.example.holamundo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holamundo.R;
import com.example.holamundo.model.Park;

import java.util.List;

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ParkViewHolder> {
    private final Context mContext;

    private List<Park> parks;

    // Creamos una variable global private final ParkAdapterOnClickHandler llamado clickHandler
    // que recibirá una instancia que implemente la interface ParkAdapterOnClickHandler definida
    // más abajo.
    private final ParkAdapterOnClickHandler clickHandler;

    /**
     * Se define la interface que recibe los mensajes onClick.
     */
    public interface ParkAdapterOnClickHandler {
        void onClick(Park park);
    }

    // Constructor de ParkAdapter, este va a recibir el contexto de ejecución de la app,
    // una instancia que implementa ParkAdapterOnClickHandler y un listado de parques
    public ParkAdapter(Context context, ParkAdapterOnClickHandler clickHandler, List<Park> parks) {
        this.mContext = context;
        this.clickHandler = clickHandler;
        this.parks = parks;
    }

    @NonNull
    @Override
    public ParkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.hm_card_landscape, parent, false);
        return new ParkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkViewHolder holder, int position) {
        Park park = parks.get(position);
        holder.imageViewPicture.setImageResource(park.getPicture());
        holder.textViewName.setText(park.getName());
    }

    @Override
    public int getItemCount() {
        return parks.size();
    }

    class ParkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Elementos de UI a rellenar
        public TextView textViewName;
        public ImageView imageViewPicture;

        public ParkViewHolder(@NonNull View itemView) {
            // Recibe la View completa. La pasa al constructor padre y enlazamos referencias UI
            // con nuestras propiedades ParkViewHolder declaradas justo arriba.
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewCardPark);
            imageViewPicture = itemView.findViewById(R.id.imageViewCardPark);
            // Registramos un callback para ser invocado cuando esta vista sea clicqueada.
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Obtiene la posición de la vista en el adapter
            int mItemSelected = getAdapterPosition();

            // Envia el parque a través del clic
            clickHandler.onClick(parks.get(mItemSelected));
        }
    }
}
