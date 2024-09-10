package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List; 

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	
	protected List<Tiquete> tiquetes;
	
    // Constructor
    public Cliente() {
    	this.tiquetes = new ArrayList<>();
    }

    // Métodos abstractos 
    
    public abstract String getTipoCliente();
    public abstract String getIdentificador();

    // Métodos concretos 
    
    public void agregarTiquete(Tiquete tiquete) {
        tiquetes.add(tiquete);
    }

    public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete tiquete : tiquetes) {
            total += tiquete.getTarifa(); 
        }
        return total;
    }

    public void usarTiquetes(Vuelo vuelo) {
        List<Tiquete> tiquetesUsados = new ArrayList<>();
        for (Tiquete tiquete : tiquetes) {
            if (tiquete.getVuelo().equals(vuelo) && !tiquete.esUsado()) { 
                tiquete.marcarComoUsado(); 
                tiquetesUsados.add(tiquete);
            }
        }
        tiquetes.removeAll(tiquetesUsados);
    }
}