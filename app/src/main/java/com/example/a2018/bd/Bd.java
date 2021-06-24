package com.example.a2018.bd;

public class Bd {
    public static final String TABLA_USUARIOS="usuario";
    public static final String CAMPO_ID_USUARIO="id_usuario";
    public static final String CAMPO_EMAIL="email";
    public static final String CAMPO_NOMBRES="nombres";
    public static final String CAMPO_APELLIDOS="apellidos";
    public static final String CAMPO_PASSWORD="password";
    public static final String CAMPO_ROL="rol";

    public static final String CREAR_TABLA_USUARIOS="CREATE TABLE " +
            ""+TABLA_USUARIOS+" ("+CAMPO_ID_USUARIO+" "+" INTEGER,"+CAMPO_EMAIL+" TEXT, "+CAMPO_NOMBRES+" TEXT,"+CAMPO_APELLIDOS+" TEXT, "+CAMPO_PASSWORD+" TEXT, "+CAMPO_ROL+" INTEGER)";

    //Constantes campos tabla rol
    public static final String TABLA_ROLES="rol";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombres";

    public static final String CREAR_TABLA_ROLES="CREATE TABLE " +
            ""+TABLA_ROLES+" ("+CAMPO_ID+"  INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE+" TEXT) ";
}
git mas