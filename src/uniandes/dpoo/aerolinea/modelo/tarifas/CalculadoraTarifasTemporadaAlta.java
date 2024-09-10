package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	
	protected static final int COSTO_POR_KM = 1000;

	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		return distancia * COSTO_POR_KM;
    }

	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		return 0.0;
	}

}
