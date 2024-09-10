package uniandes.dpoo.aerolinea.persistencia;

import org.json.JSONArray;
import org.json.JSONObject;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());

            JSONArray clientesJson = jsonObject.getJSONArray("clientes");
            for (int i = 0; i < clientesJson.length(); i++) {
                JSONObject clienteJson = clientesJson.getJSONObject(i);
                Cliente cliente;
                if (clienteJson.getString("tipoCliente").equals("Natural")) {
                    cliente = new ClienteNatural(clienteJson.getString("nombre"));
                } else {
                    cliente = new ClienteCorporativo(clienteJson.getString("nombreEmpresa"), clienteJson.getInt("tamanoEmpresa"));
                }
                aerolinea.agregarCliente(cliente);
            }


            JSONArray rutasJson = jsonObject.getJSONArray("rutas");
            for (int i = 0; i < rutasJson.length(); i++) {
                JSONObject rutaJson = rutasJson.getJSONObject(i);
                Ruta ruta = new Ruta(null, null, rutaJson.getString("horaSalida"), rutaJson.getString("horaLlegada"), rutaJson.getString("codigo"));
                aerolinea.agregarRuta(ruta);
            }

            JSONArray vuelosJson = jsonObject.getJSONArray("vuelos");
            for (int i = 0; i < vuelosJson.length(); i++) {
                JSONObject vueloJson = vuelosJson.getJSONObject(i);
                Ruta ruta = aerolinea.getRuta(vueloJson.getString("codigoRuta"));
                Vuelo vuelo = new Vuelo(ruta, vueloJson.getString("fecha"), null);
                aerolinea.agregarVuelo(vuelo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) {
        JSONObject jsonObject = new JSONObject();
        
        JSONArray clientesJson = new JSONArray();
        for (Cliente cliente : aerolinea.getClientes()) {
            JSONObject clienteJson = new JSONObject();
            if (cliente instanceof ClienteNatural) {
                clienteJson.put("tipoCliente", "Natural");
                clienteJson.put("nombre", ((ClienteNatural) cliente).getIdentificador());
            } else {
                clienteJson.put("tipoCliente", "Corporativo");
                clienteJson.put("nombreEmpresa", ((ClienteCorporativo) cliente).getNombreEmpresa());
                clienteJson.put("tamanoEmpresa", ((ClienteCorporativo) cliente).getTamanoEmpresa());
            }
            clientesJson.put(clienteJson);
        }
        jsonObject.put("clientes", clientesJson);
        
 
        JSONArray rutasJson = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject rutaJson = new JSONObject();
            rutaJson.put("codigo", ruta.getCodigoRuta());
            rutaJson.put("origen", ruta.getOrigen());
            rutaJson.put("destino", ruta.getDestino());
            rutasJson.put(rutaJson);
        }
        jsonObject.put("rutas", rutasJson);

        JSONArray vuelosJson = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            JSONObject vueloJson = new JSONObject();
            vueloJson.put("codigoRuta", vuelo.getRuta().getCodigoRuta());
            vueloJson.put("fecha", vuelo.getFecha());
            vuelosJson.put(vueloJson);
        }
        jsonObject.put("vuelos", vuelosJson);

        try (FileWriter fileWriter = new FileWriter(archivo)) {
            fileWriter.write(jsonObject.toString(4)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

