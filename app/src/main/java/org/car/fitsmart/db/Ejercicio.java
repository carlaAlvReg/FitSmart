package org.car.fitsmart.db;

public class Ejercicio {

    private int id;
    private String nombre;

    private String nivel_dificultad;
    private String grupo_muscular;
    private String num_series;
    private String num_repes;
    private String dia;

    public String getNum_series() {
        return num_series;
    }

    public void setNum_series(String num_series) {
        this.num_series = num_series;
    }

    public String getNum_repes() {
        return num_repes;
    }

    public void setNum_repes(String num_repes) {
        this.num_repes = num_repes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }


    public Ejercicio(){

    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivel_dificultad(String nivelDificultad) {
        this.nivel_dificultad = nivelDificultad;
    }

    public void setGrupo_muscular(String grupoMuscular) {
        this.grupo_muscular = grupoMuscular;
    }

    public String getNivel_dificultad() {
        return nivel_dificultad;
    }

    public String getGrupo_muscular() {
        return grupo_muscular;
    }

    public int getId() {
        return this.id;
    }

    public CharSequence getNombre() {
        return this.nombre;
    }
}
