package Modelo;

/**
 * [Validaciones] Clase que permite validar las excepciones
 * @since 04/10/2017
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class Validaciones {

//=======================================================================================================

/**
 * Metodo generico que permite validar 2 tipos de excepciones y si los campos estan vacios,se debe inicilizar con la cantidad
 * de elementos tipo String, tipo Int o tipo Double que se quieran validar [0 si no hay ninguno de ese tipo]
 * @param arregloValidar Almacena los datos ingresados en un solo array para recorrerlo mediante condicionales
 * @param cantidadString Almacena la cantidad real de datos que son String del array a validar 
 * @param cantidadInt Almacena la cantidad real de datos que son Enteros del array a validar
 * @param cantidadDouble Almacena la cantidad real de datos que son Doubles del array a validar
 * @return un String vacio en caso de que todas las validaciones sean correctas o el mensaje de error en caso de que no se cumpla la condicion
 */

    public String validarExcepciones(String arregloValidar[],int cantidadString, int cantidadInt, int cantidadDouble)
      {
          String imprimir="";
          int tamañoArray = arregloValidar.length; 
          if (cantidadString >=0)
          {
              int inicio = 0;
              int fin = tamañoArray;
              while (inicio<fin)
              {
                  if(arregloValidar[inicio].equals(""))
                  {
                      return "Algun campo se encuentra vacio porfavor validar";
                  }      
                  inicio++;
              }
    
          }
          
          if (cantidadInt !=0)
          {
              int inicio = cantidadString;
              int fin = cantidadString + cantidadInt;
              while (inicio<fin)
              {
                  try{
                      int numero = Integer.parseInt(arregloValidar[inicio]);
                  }
                  catch (NumberFormatException e){
                      return "Tenga en cuenta que el dato ingresado: '" +arregloValidar[inicio]+"' debe ser de tipo entero";
                  }
                  inicio++;
              } 
              
          }
          
          if (cantidadDouble !=0)
          {
              int inicio = cantidadString + cantidadInt;
              int fin = tamañoArray;
              while (inicio<fin)
              {
                  try{
                      double numero = Double.parseDouble(arregloValidar[inicio]);
                  }
                  catch (NumberFormatException e){
                      return "Tenga en cuenta que el dato ingresado: '" +arregloValidar[inicio]+"' debe ser de tipo double";
                  }
                  inicio++;
              }       
          }
          
          return imprimir;      
      }
//=======================================================================================================
}


