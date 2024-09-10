package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo; 
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	
	protected static final int COSTO_POR_KM_NATURAL = 600;
	protected static final int COSTO_POR_KM_CORPORATIVO = 900;
	protected static final double DESCUENTO_PEQ = 0.02;
	protected static final double DESCUENTO_MEDIANAS = 0.1;
	protected static final double DESCUENTO_GRANDES = 0.2;

	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int costoPorKm;
		String tipoCliente = cliente.getTipoCliente();
		
		if (tipoCliente.equals("CORPORATIVO")) {
			costoPorKm = COSTO_POR_KM_CORPORATIVO;
		} else {
			costoPorKm = COSTO_POR_KM_NATURAL;
		}
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		return distancia * costoPorKm;
    }

	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		if (cliente.getTipoCliente().equals("CORPORATIVO")) {
			ClienteCorporativo clienteCorporativo = (ClienteCorporativo) cliente;
			int tamanoEmpresa = clienteCorporativo.getTamanoEmpresa();
			
			if (tamanoEmpresa == 3) {
				return DESCUENTO_PEQ;
			} else if (tamanoEmpresa == 2) {
				return DESCUENTO_MEDIANAS;
			} else if (tamanoEmpresa ==1) {
				return DESCUENTO_GRANDES;
			}
		}
		
		// Si no se cumple ninguna condición, no hay descuento
		return 0.0;
	}
	
	
	
}