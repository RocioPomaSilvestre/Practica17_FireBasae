package com.rocio.poma.practica17_firebasae;

public class Estado {
    String id;
    String estado;

    public Estado(){

    }

    public Estado(String id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getEstado();
    }
}
