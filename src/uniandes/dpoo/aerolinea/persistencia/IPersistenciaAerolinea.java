package uniandes.dpoo.aerolinea.persistencia;

import java.io.IOException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public interface IPersistenciaAerolinea {

    /**
     * Carga la información de una aerolínea desde un archivo.
     * 
     * @param archivo La ruta al archivo desde donde se cargará la información
     * @param aerolinea El objeto Aerolinea donde se almacenará la información cargada
     * @throws IOException Si ocurre un error durante la lectura del archivo
     */
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException;

    /**
     * Guarda la información de una aerolínea en un archivo.
     * 
     * @param archivo La ruta al archivo donde se guardará la información
     * @param aerolinea El objeto Aerolinea que contiene la información que se desea guardar
     * @throws IOException Si ocurre un error durante la escritura del archivo
     */
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException;
}