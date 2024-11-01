/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package er_comprobacion;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author javi1
 */
public class Nodo {
    private String estado;
    private Map<String, Nodo> conexiones;

    public Nodo(String estado) {
        this.estado = estado;
        this.conexiones = new HashMap<>();
    }

    public String getEstado() {
        return estado;
    }

    public Map<String, Nodo> getConexiones() {
        return conexiones;
    }

    public void agregarConexion(String dato, Nodo nodo) {
        conexiones.put(dato, nodo);
    }
}
