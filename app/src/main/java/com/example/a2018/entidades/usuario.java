package com.example.a2018.entidades;

import java.io.Serializable;

public class usuario implements Serializable {
    private Integer id_usuario;
    private String email;
    private String nombres;
    private String apellidos;
    private String password;
    private Integer rol;

    public usuario (Integer id_usuario, String email, String nombres, String apellidos, String password, Integer rol){

        this.id_usuario=id_usuario;
        this.email=email;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.password=password;
        this.rol=rol;
    }
    public usuario() {

    }
    public Integer getId_usuario(){return id_usuario;}

    public void setId_usuario(Integer id_usuario ){this.id_usuario=id_usuario;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNombres() {return nombres;}

    public void setNombres(String nombres) {this.nombres=nombres;}

    public String getApellidos () {return apellidos;}

    public void setApellidos(String apellidos){this.apellidos=apellidos;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password=password;}

    public Integer getRol(){return rol;}

    public void setRol(Integer rol){this.rol=rol;}

}
