package julianzamora.com.recoleccionesapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by frontend on 6/03/18.
 */

public class GridAdapter  extends BaseAdapter {

    private final Context mContext;
    private ArrayList<Recoleccion> lista;


    public GridAdapter(Context context, ArrayList<Recoleccion> lista) {
        this.mContext = context;
        this.lista = lista;
    }


    @Override
    public int getCount() {
        return (lista!= null) ? lista.size() : 0 ;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Recoleccion r = lista.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }

        final TextView txemp = (TextView) convertView.findViewById(R.id.Empresatxgrid);
        final TextView txfech = (TextView) convertView.findViewById(R.id.Fechatxgrid);
        final TextView txtipo = (TextView) convertView.findViewById(R.id.Residuotxgrid);

        txemp.setText(r.getEmpresa());
        txfech.setText(r.getFecha());
        String[] items = this.mContext.getResources().getStringArray(R.array.lista_residuos);
        txtipo.setText(items[r.getTipo()]);

        return convertView;
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

