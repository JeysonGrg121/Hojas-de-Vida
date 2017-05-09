/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Jeyson Duarte
 */

/*
clase aspirante, aqui van los datos de las personas que se ingresas, la dirreccion de la imagen, el nombre el apellid, y los demas 
datos necesarios para crear al hoja del aspirante.
*/
public class Aspirante {
   /*
    todas las variables que se necesitas, el nombre el apellido la fecha de baciemiento etc
    */
    private String nombre;
    private String apellido;
    private String imagen;
    private double cedula;
    private String correo;
    private Profesion profesion;
    private String fechaDeNacimiento;
    private int edad;
    
    
    /*
    constructor de la calse
    */
    public Aspirante(String nombre, String apellido, String imagen, double cedula, String correo,Profesion profesion, String fechaDeNacimiento, int edad ){
        
        this.nombre = nombre;
        this.apellido =  apellido;
        this.imagen =  imagen;
        this.cedula =  cedula;
        this.correo =  correo;
        this.profesion =  profesion;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.edad = edad;
        
    }
    
    /*
    Getters and Setter de la clase
    */
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }


    public String getImagen(){
        return imagen;
    }
    public void setImagen(String imagen){
        this.imagen =  imagen;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public void  setApellido(String Apellido){
        this.apellido = apellido;
    }

    public double getCedula() {
        return cedula;
    }

    public void setCedula(double cedula) {
        this.cedula = cedula;
    }
 
    public String getCorreo(){
        return correo;
    }  
    public void setCorreo(String Correo){
        this.correo = correo;
    }
    public Profesion getProfesion(){
        return profesion;
    }
    public void setProfesion(Profesion profesion){
        this.profesion = profesion;
    }
    

    
    
    }
    
    
    

