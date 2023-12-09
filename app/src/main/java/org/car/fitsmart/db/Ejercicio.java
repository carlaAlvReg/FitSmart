package org.car.fitsmart.db;

public class Ejercicio {

    private int id;
    private String nombre;
    private String nivel_dificultad;
    private String grupo_muscular;
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

    public void setGrupo_mucular(String grupoMucular) {
        this.grupo_muscular = grupoMucular;
    }

    public int getId() {
        return this.id;
    }

    public CharSequence getNombre() {
        return this.nombre;
    }
}
