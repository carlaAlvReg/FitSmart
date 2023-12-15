package org.car.fitsmart.db;

public class Persona {
    private String nombre;
    private String peso;
    private String altura;
    private String sexo;
    private String edad;
   private String pesoDeseado;

   private int Id;
    public Persona(){

    }



    public String getNombre() {
        return nombre;
    }

    public String getPeso() {
        return peso;
    }

    public String getAltura() {
        return altura;
    }
    public int getId(){
        return Id;
    }

    public void setId(int Id){
        this.Id = Id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setPesoDeseado(String pesoDeseado) {
        this.pesoDeseado = pesoDeseado;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEdad() {
        return edad;
    }

    public String getPesoDeseado() {
        return pesoDeseado;
    }




}
