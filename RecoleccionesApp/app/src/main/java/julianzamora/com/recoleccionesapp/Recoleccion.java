package julianzamora.com.recoleccionesapp;

/**
 * Created by frontend on 5/03/18.
 */

public class Recoleccion {
    public String fecha;
    public String empresa;
    public int tipo;
    public int id;

    public Recoleccion(String fecha, String empresa, int tipo, int id ){

        this.fecha = fecha;
        this.empresa = empresa;
        this.tipo = tipo;
        this.id = id;
    }
    public Recoleccion(String fecha, String empresa, int tipo ){

        this.fecha = fecha;
        this.empresa = empresa;
        this.tipo = tipo;

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
