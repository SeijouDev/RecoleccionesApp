package julianzamora.com.recoleccionesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frontend on 5/03/18.
 */

public class AdapterRecolecciones extends RecyclerView.Adapter <AdapterRecolecciones.MyViewHolder> {

    private List<Recoleccion> lista;
    private Context mContext;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView fecha, empresa, tipo;
        private OnClickHandler handler;

        public MyViewHolder(View view, OnClickHandler handler) {
            super(view);
            fecha = (TextView) view.findViewById(R.id.Fechatx);
            empresa = (TextView) view.findViewById(R.id.Empresatx);
            tipo = (TextView) view.findViewById(R.id.Residuotx);
            this.handler = handler;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            handler.onItemClick(v, getAdapterPosition());
        }
    }


    private OnClickHandler handler;
    public AdapterRecolecciones(List<Recoleccion> la, Context c, OnClickHandler handler) {
        this.lista = la;
        this.mContext = c;
        this.handler = handler;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        return new MyViewHolder(itemView, this.handler);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Recoleccion r = lista.get(position);
        holder.fecha.setText(r.getFecha());
        holder.empresa.setText(r.getEmpresa());
        String[] items = this.mContext.getResources().getStringArray(R.array.lista_residuos);
        holder.tipo.setText(items[r.getTipo()]);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void remove(int i){
        lista.remove(i);
    }

    public void clear(){

        if(lista.size() > 0)
            lista = new ArrayList<>();

        Log.e("test","Lista size -> " + lista.size());
    }


}
