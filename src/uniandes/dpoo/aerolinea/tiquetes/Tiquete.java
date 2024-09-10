package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo; 
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
    // Atributos
    private String codigo;
    private int tarifa;
    private boolean usado;
    private Vuelo vuelo;
    private Cliente clienteComprador;

    // Constructor
    public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
        this.codigo = codigo;
        this.vuelo = vuelo;
        this.clienteComprador = clienteComprador;
        this.tarifa = tarifa;
        this.usado = false;
    }

    // Métodos
    public String getCodigo() {
        return codigo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Cliente getCliente() {
        return clienteComprador;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void marcarComoUsado() {
        this.usado = true;
    }

    public boolean esUsado() {
        return usado;
    }
}

