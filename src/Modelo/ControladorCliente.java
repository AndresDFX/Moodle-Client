package Modelo;

import Vista.Menu;
import java.io.*;
import java.net.*;

//=======================================================================================================
/**
 * [ControladorCliente] Clase que continiente la logica del cliente
 * @since 24/11/2017
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class ControladorCliente {
    
    //Declaracion de Variables de Conexion
    private String IP = "localhost";
    private int puerto = 4000;
    private final String direccionInet = "235.1.1.1";
        
    //Declaracion de Variables de Flujos
    private DataOutputStream flujoSalida = null;
    private ObjectInputStream flujoEntrada = null;
    private MulticastSocket socketMulti = null;  
    private Socket socket = null;
  
    //Declaracion de Variables Auxiliares
    private Thread escuchaMulticast;
    private boolean datosConexion;
    private int tamanoPreguntas;
    private String preguntaOcupada="#";
    private String preguntaRespondidas="#";   
    private String calificacion="";
    private String usuarioActual;

//=======================================================================================================  
    /**
     * Metodo que permite abrir la conexion con el servidor 
     */
    public void iniciarCliente() 
    {    
        mostrarMensajes("Estableciendo conexion espere un momento..."); 
        abrirConexion();
        iniciarEscucha();             
    }
//======================================================================================================= 
    /**
     * Metodo que envia el identificador N y el numero de pregunta atravez del Unicast para solicitar una pregunta
     * @param numeroPregunta numero de pregunta a solicitar al servidor
     */
    public void pedirPregunta(int numeroPregunta)
    {          
        try {
                flujoSalida.writeUTF("N"+numeroPregunta);
                mostrarMensajes("Se le ha solicitado al servidor la pregunta # "+ (numeroPregunta+1));
                flujoSalida.flush();               
            }
            
        catch(IOException  ioe){  
                mostrarMensajes("Error al pedir pregunta: "+ioe.getMessage());
            }
            
    }
//=======================================================================================================  
    
    /**
     * Metodo que envia el identificador L y el numero de pregunta atravez del Unicast para poner la pregunta libre
     * @param numeroPregunta numero de pregunta actual que tiene el cliente
     */
    
    public void enviarLibre(int numeroPregunta)
    {        
        try {  
                flujoSalida.writeUTF("L"+numeroPregunta);
                flujoSalida.flush();
                mostrarMensajes("Estado de pregunta actual: "+ (numeroPregunta+1) + " enviado correctamente");
        }
            
        catch(IOException  ioe){  
                mostrarMensajes("Error: "+ioe.getMessage());
            }
            
    }
 //======================================================================================================= 
    /**
     * Metodo que envia el identificador R y el numero de pregunta y la respuesta atravez del Unicast para poner la pregunta respondida
     * @param numeroPregunta Variable que almacena el numero de pregunta actual
     * @param respuesta Variable que almacena la respuesta enviada por el cliente
     */
    
    public void enviarRespuesta(int numeroPregunta, String respuesta )
    {        
        try {  
                flujoSalida.writeUTF("R"+numeroPregunta+respuesta+usuarioActual);
                flujoSalida.flush();
                mostrarMensajes("Se ha enviado correctamente la respuesta a la pregunta: "+ (numeroPregunta+1));
        }
            
        catch(IOException  ioe){  
                mostrarMensajes("Error al enviar respuesta: "+ioe.getMessage());
            }           
    }
//=======================================================================================================        
    
        /**
     * Metodo que permite enviar al servidor un identificador T y 0, para indicarle que el examen termino
     */
    public void enviarTerminado()
    {        
        try {  
                flujoSalida.writeUTF("T"+0);
                flujoSalida.flush();
        }        
        catch(IOException  ioe){  
                mostrarMensajes("Error: "+ioe.getMessage());
            }           
    }
  
//=======================================================================================================    
    /**
     * Metodo que recibe un tipo object por el unicast y hace un casting a la clase Pregunta
     * @return Objeto pregunta recibido atravez del flujo
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Preguntas recibirPregunta() throws IOException, ClassNotFoundException   
    {            
        Preguntas pregunta = (Preguntas) flujoEntrada.readObject();
        return pregunta;
    }

//=======================================================================================================  
    /**
     * Metodo encargado de cerrar los flujos y el socket
     */   
    public void cerrarFlujos()
    {  
        try{            
            flujoSalida.close();
            socket.close();
            flujoEntrada.close();     
            socketMulti.close();
            mostrarMensajes("Flujos cerrados por desconexion del Servidor");
        }
        catch(IOException ioe){  
            mostrarMensajes("Error al cerrar flujos: "+ioe.getMessage());
        }
    }
//=======================================================================================================     
    /**
     * Metodo encargado de inicializar las variables necesarias para la conexion con los clientes
     */
    public void abrirConexion() 
    { 
        try
        {                
            socketMulti = new MulticastSocket(puerto);
            socketMulti.joinGroup(InetAddress.getByName(direccionInet));
            socket = new Socket(IP,puerto);
            flujoSalida = new DataOutputStream(socket.getOutputStream());
            flujoEntrada = new ObjectInputStream(socket.getInputStream());
            flujoSalida.flush();
            datosConexion = true;
            mostrarMensajes("Conectado a: "+IP+" socket: "+socket);
         
        }
        catch(IOException e){
            mostrarMensajes("Error al abrir la conexion: "+e.getMessage());
            datosConexion = false;
        }
               
    }
//=======================================================================================================   
    /**
     * Metodo para mostrar mensajes por pantalla de las acciones del cliente
     * @param mensaje Variable que determina el mensaje a mostrar
     */
    public void mostrarMensajes(String mensaje)
    {
        System.out.println(mensaje);
    }   
//=======================================================================================================        
    /**
     * Metodo encargado de escuchar los mensajes Multicast que envia el servidor
     */    
    public void escuchandoMulticast()
    {        
        while(true)
        {           
            try{               
                byte [] buffer = new byte [1000];
                DatagramPacket dgp = new DatagramPacket (buffer, buffer.length);
                byte[] buffer2= ajusteDatagrama(dgp);
                String salida = new String (buffer2);
                String [] texto = salida.split("@");               
                Menu.jLabelTemp.setText(texto[0]);
                tamanoPreguntas = Integer.parseInt(texto[1]); 
                Menu.estadoExamen = Integer.parseInt(texto[2]);
                preguntaRespondidas = texto[3];
                calificacion = texto[4];
                verificarEstado(Menu.estadoExamen);
                                               
            }catch(IOException e){
                mostrarMensajes("Error en el multicast al recibir en el cliente");
              
            }
        } 
   }     
//=======================================================================================================
    /**
     * Metodo que retorna el tamaño de las preguntas del examen
     * @return El tamaño del arreglo de preguntas enviado desde el server
     */
    public int tamanoPreguntas()
    {
        return tamanoPreguntas;
    }        
//=======================================================================================================    
    /**
     * Metodo que permite crear el hilo para escuchar constanmente el tiempo 
     * y el tamaño de las preguntas
     */
    public void iniciarEscucha() 
    {
        escuchaMulticast = new Thread(){
        @Override
        public void run(){
            escuchandoMulticast();
        }
        };
        escuchaMulticast.start();
    }

//=======================================================================================================
    /**
     * Metodo que permite obtener la cadena con las preguntas ocupadas enviado desde el servidor
     * @return La cadena con las preguntas ocupadas
     */    
    public String getOcupadas() 
    {
        try{
            return preguntaOcupada.substring(2);   
        }
        catch (StringIndexOutOfBoundsException | NullPointerException ex)
        {
            return "-1";
        }                         
    }

//=======================================================================================================
    /**
     * Metodo que permite obtener la cadena con las preguntas respondidas enviado desde el servidor
     * @return La cadena con las preguntas respondidas
     */
    public String getRespondidas()
    {
        try{
            return preguntaRespondidas.substring(2);   
        }
        catch (StringIndexOutOfBoundsException ex)
        {
            return "-1";
        }           
    }
//=======================================================================================================    
    /**
     * Metodo que permite obtener los datos de conexion con el servidor
     * @return El valor almacenado en la variable datos de conexion
     */
    public boolean getDatosConexion() {
        
        return datosConexion;
    }

//======================================================================================================= 
    /**
     * Metodo que permite guardar todos los datos necesarios para conexion con el server
     * @param usuario Variable que almacena el nombre de usuario del Cliente (estudiante)
     * @param IP Variable que almacena la IP del servidor(profesor)
     * @param puerto Variable que almacena el puerto por el cual se va realizar la conexion Unicast y Multicast
     */
    public void setDatosConexion(String usuario, String IP, String puerto) {
        
        this.usuarioActual = usuario;
        this.IP = IP;
        this.puerto = Integer.valueOf(puerto);
                
    }
//=======================================================================================================   
    /**
     * Metodo que verifica constantemente el estado del examen 1 para iniciado, 2 para terminado
     * 0 es el estado inicial para iniciar el examen y el 3 significa que el servidor se desconecto
     * @param estadoExamen Variable que almacena el estado del examen con un valor numerico de 0-3
     */
    
    public void verificarEstado(int estadoExamen) 
    {        
        switch (estadoExamen) {
       
            case 1:
                Menu.jButtonPreguntas.setEnabled(true);
                Menu.jListPreguntas.setEnabled(true);
                Menu.jLabelCalificacion.setText("");
                break;
                
            case 2:
                enviarTerminado();
                Menu.jButtonPreguntas.setEnabled(false);
                Menu.jListPreguntas.setEnabled(false);
                Menu.jLabelCalificacion.setText("Calificacion: "+calificacion);
                Menu.jLabelTemp.setText("");
                break;
                
            case 3:                
                cerrarFlujos();                
                break;
        }
    }
//=======================================================================================================
    /**
     * Metodo que recibe lo enviado por el Multicast y ajusta el tamaño en bytes del datagrama
     * @param mensaje Variable que almacena el mensaje que entra por el multicast
     * @return el mensaje enviado por el multicast ajustado en tamaño
     * @throws IOException 
     */
    public byte[] ajusteDatagrama(DatagramPacket mensaje) throws IOException
    {      
        socketMulti.receive(mensaje);
        byte [] buffer = new byte [mensaje.getLength ()];
        System.arraycopy (mensaje.getData (),0,buffer,0, mensaje.getLength ());
        return buffer;
    }
     
//======================================================================================================= 


}
   

