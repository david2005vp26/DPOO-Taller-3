package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

public class ClienteNatural extends Cliente {

    public static final String NATURAL = "Natural";
    private String nombre;

    // Constructor
    public ClienteNatural(String nombre) {
        this.nombre = nombre;
    }

    // Métodos

    @Override
    public String getTipoCliente() {
        return NATURAL;
    }

    @Override
    public String getIdentificador() {
        return nombre;
    }

}