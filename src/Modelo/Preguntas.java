package Modelo;
import java.io.Serializable;

//=======================================================================================================       
/**
 * [Preguntas] Clase plantilla para la creacion de objetos Pregunta
 * @since 24/11/2017
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class Preguntas implements Serializable{
    
    private static final long serialVersionUID = -4507489610617393544L;    
    private String nombre;
    private String descripcion;
    private String[]opciones;
    private String correcta;
    private String estado;
    private String calificacion;
    private String quienRespondio;
    
//=======================================================================================================       
    
    /**
     * Constructor de la clase Preguntas
     * @param nombre Variable que almacena el nombre de la pregunta
     * @param descripcion Variable que almacena el cuerpo de la pregunta
     * @param opciones Variable que almacena las opciones de respuesta
     * @param correcta Variable que almacena la opcion correcta
     * @param estado Variable que almacena uno de los tres estados de pregunta : Libre, Ocupada, Respondida
     * @param calificacion Variable que almacena uno de los dos estados de calificacion: Buena o Mala
     * @param quienRespondio Variable que almacena el numero de cliente que respondio la pregunta
     * @since Preguntas.java
     */

    public Preguntas(String nombre, String descripcion, String[] opciones, String correcta, String estado,String calificacion, String quienRespondio) {        
        
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.opciones = opciones;
        this.correcta = correcta;
        this.estado = estado;
        this.calificacion = calificacion;
        this.quienRespondio = quienRespondio;
    }

//======================================================================================================= 
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the opciones
     */
    public String[] getOpciones() {
        return opciones;
    }

    /**
     * @return the correcta
     */
    public String getCorrecta() {
        return correcta;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }
    
    
    public String getCalificacion() {
        return calificacion;
    }

    /**
     * @return the quienRespondio
     */
    public String getQuienRespondio() {
        return quienRespondio;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    /**
     * @param correcta the correcta to set
     */
    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @param quienRespondio the quienRespondio to set
     */
    public void setQuienRespondio(String quienRespondio) {
        this.quienRespondio = quienRespondio;
    }
//=======================================================================================================        
}
