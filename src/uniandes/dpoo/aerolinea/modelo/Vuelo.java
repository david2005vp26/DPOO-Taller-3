package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection; 
import java.util.HashSet;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
    // Atributos
    private String fecha;
	private Ruta ruta;
	private Avion avion;
	private Collection<Tiquete> tiquetes;

    
    // Constructor
    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.fecha = fecha;
        this.ruta = ruta;
        this.avion = avion;
        this.tiquetes = new HashSet<>();

    }
    
    
    // Métodos

	public String getFecha() {
		return fecha;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public Avion getAvion() {
		return avion;
	}
	
	public Collection<Tiquete> getTiquetes() {
        return tiquetes;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
	    int tiquetesVendidos = 0;

	    int capacidad = avion.getCapacidad(); 
	    if (tiquetes.size() + cantidad > capacidad) {
	        return tiquetesVendidos; 
	    }else{
	    	for (int i = 0; i < cantidad; i++) {
	            int tarifa = calculadora.calcularTarifa(this, cliente); 
	            Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
	            tiquetes.add(tiquete); 
	            tiquetesVendidos++; 
	        }
	    }

	    return tiquetesVendidos;   	
	}
	

    /**
     * Compara este vuelo con otro objeto para verificar si son iguales.
     * 
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vuelo otroVuelo = (Vuelo) obj;
        return fecha.equals(otroVuelo.fecha) && ruta.equals(otroVuelo.ruta) && avion.equals(otroVuelo.avion);
    }
    
}