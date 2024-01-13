/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author andre
 */
public class Pruebas {

    public static boolean validarNombre(String texto) {
        String patron = "^[a-zA-Z ]*$";
        return texto.matches(patron);
    }
    public static void main(String[] args) {
        String texto1 = "SoloLe768tras";


        // Validar los textos
        
        if(validarNombre(texto1)){
        System.out.println("es válido");
        } 
        
        if(!validarNombre(texto1)) {
            System.out.println("no es valido");
        }
}
}





      /*    
        public static boolean validarNumeroTelefono(String numeroTelefono) {
        // Eliminar espacios y guiones del número de teléfono
        String numeroSinFormato = numeroTelefono.replaceAll("[\\s-]+", "");

        // Definir el patrón para el formato del número de teléfono (10 a 11 dígitos con opcional signo de + al principio)
        String patron = "^\\+?[0-9]{10,11}$";

        // Compilar el patrón
        Pattern pattern = Pattern.compile(patron);

        // Crear un objeto Matcher
        Matcher matcher = pattern.matcher(numeroSinFormato);

        // Verificar si coincide con el patrón
        return matcher.matches();
} */
        
   /*     
        public static boolean validarCedula(String numeroTelefono) {
        String patron = "^[0-9][6,8]$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(numeroTelefono);
        return matcher.matches();
    }*/
