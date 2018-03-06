package julianzamora.com.recoleccionesapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickHandler {

    public ArrayList< Recoleccion> lista;
    public AdapterRecolecciones adapter;
    public Context mContext;
    public RecyclerView recycler;
    public GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        recycler = findViewById(R.id.lista);
        grid = findViewById(R.id.grid);

        Button boton=(Button)findViewById(R.id.btn1);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), FormularioActivity.class));
            finish();
            }
        });

        Button botonBorrarTodo = (Button)findViewById(R.id.btnBorrarTodo);
        botonBorrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Esta seguro que desea borrar todas las recolecciones ?")
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                            db.BorrarRecolecciones();
                            adapter.clear();
                            lista = db.ObtenerRecolecciones();
                            adapter.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });



        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        if(rotation == 1 || rotation == 3)
            mostaGrilla();
        else
            mostrarLista();


        Log.e("test","Rotation: "+rotation);
    }

    @Override
    public void onItemClick(View v, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Esta seguro que desea borrar esta recolección ?")
            .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    db.BorrarRecoleccion(lista.get(i).id);
                    lista = db.ObtenerRecolecciones();
                    adapter.remove(i);
                    adapter.notifyDataSetChanged();
                }
            });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            mostaGrilla();
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            mostrarLista();

        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        Log.e("test","Rotation: "+rotation);
    }

    public void mostrarLista(){
        recycler.setVisibility(View.VISIBLE);
        grid.setVisibility(View.GONE);
        DatabaseHelper databasehelper = new DatabaseHelper(getApplicationContext());
        lista = databasehelper.ObtenerRecolecciones();
        adapter = new AdapterRecolecciones(lista,getApplicationContext(), this);
        RecyclerView.LayoutManager lm= new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
    }

    public void mostaGrilla(){
        recycler.setVisibility(View.GONE);
        grid.setVisibility(View.VISIBLE);
        DatabaseHelper databasehelper = new DatabaseHelper(getApplicationContext());
        lista = databasehelper.ObtenerRecolecciones();
        final GridAdapter gadapter = new GridAdapter(getApplicationContext(), lista);
        grid.setAdapter(gadapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, final int i, long id) {
                Recoleccion r = lista.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Esta seguro que desea borrar esta recolección ?")
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                db.BorrarRecoleccion(lista.get(i).id);
                                lista = db.ObtenerRecolecciones();
                                gadapter.remove(i);
                                gadapter.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }
}
