package julianzamora.com.recoleccionesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by frontend on 5/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "datab";
    private static final String TABLA_RESIDUOS = "residuos";
    private static final String IDRESIDUO = "id";
    private static final String FECHA = "dato_fecha";
    private static final String EMPRESA = "nombre_empresa";
    private static final String RESIDUO = "tipo_residuo";
    private static final String CREATETABLE =
            "CREATE TABLE "+TABLA_RESIDUOS +" " +
            "(" + IDRESIDUO + " integer primary key, "+
            FECHA + " text not null," +
            EMPRESA + " text not null," +
            RESIDUO + " integer not null)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_RESIDUOS);
        onCreate(db);

    }

    public long CrearRecoleccion (String fecha, String empresa, int tipo){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FECHA,fecha);
        values.put(EMPRESA,empresa);
        values.put(RESIDUO,tipo);
        long result = db.insert(TABLA_RESIDUOS, null, values);
        return result;
    }

    public ArrayList<Recoleccion> ObtenerRecolecciones(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList <Recoleccion> list = new ArrayList<>();
        Cursor res = db.query(TABLA_RESIDUOS, null, null, null, null, null, null);

        Log.e("test",""+ res.getCount());

        if(res != null && res.getCount() > 0) {
            res.moveToFirst();

            do{
                String fecha = res.getString(1);
                String empresa = res.getString(2);
                int tipo = res.getInt(3);
                int id = res.getInt(0);
                Recoleccion r = new Recoleccion(fecha, empresa, tipo, id);
                list.add(r);
            } while (res.moveToNext());
        }

        return list;

    }

    public void BorrarRecoleccion (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA_RESIDUOS,IDRESIDUO + "=" + id, null );
    }

    public void BorrarRecolecciones() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA_RESIDUOS,null, null );
    }
}
