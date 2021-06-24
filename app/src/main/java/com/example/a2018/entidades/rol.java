package com.example.a2018.entidades;

import java.io.Serializable;

public class rol implements Serializable {

    private Integer id;
    private String nombres1;

    public rol(Integer id, String nombres1){

        this.id=id;
        this.nombres1=nombres1;
    }
    public rol() {

    }
    public Integer getId(){return id;}

    public void setId(Integer id){this.id=id;}

    public String getNombres1(){return nombres1;}

    public void setNombres1(String nombres1){this.nombres1=nombres1;}

}
