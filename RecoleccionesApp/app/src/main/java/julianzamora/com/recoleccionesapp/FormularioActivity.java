package julianzamora.com.recoleccionesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by frontend on 5/03/18.
 */

public class FormularioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity);

        Calendar calend = Calendar.getInstance();
        final EditText fecha1 = (EditText) findViewById(R.id.Fechaauto);
        final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        format1.format(calend.getTime());
        fecha1.setText(format1.format(calend.getTime()));

        final Spinner spinner = (Spinner) findViewById(R.id.Formulario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lista_residuos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String[] items = getResources().getStringArray(R.array.lista_residuos);
                //Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        Button boton2 = (Button) findViewById(R.id.Guardar);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = fecha1.getText().toString();
                final EditText empresaedit = (EditText) findViewById(R.id.Empresa);
                String empresa = empresaedit.getText().toString();
                int residuospos = spinner.getSelectedItemPosition();
                Log.e("Prueba", fecha);
                Log.e("Prueba", empresa);
                Log.e("Prueba", "" + residuospos);
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                long res = db.CrearRecoleccion(fecha, empresa, residuospos);
                Log.e("prueba", "" + res);

                if (res > 0l) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error al insertar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
