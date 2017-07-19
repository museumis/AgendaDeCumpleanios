
package ejer29_t12_agendaclumpleaniosserializable;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Ismael Martín Ramírez
 * [imartinr01@informatica.iesvalledeljerteplasencia.es]
 */
public class Agenda implements Serializable{
 
    
    private String nombre;
    private LocalDate fecha;

    public Agenda() {
    }

    public Agenda(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
