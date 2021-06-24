package com.example.a2018;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2018.bd.Bd;

public class Consultar extends AppCompatActivity {
    EditText idrol,nombrerol;

    Conexion conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        conn = new Conexion(getApplicationContext(), "bd_roles", null, 1);

        idrol = (EditText) findViewById(R.id.campoidrol);
        nombrerol = (EditText) findViewById(R.id.camponombrerol);
    }
    public void onClick(View view) {
        //registrarUsuarios();
        registrarUsuariosSql();
        limpiar();
    }

    private void registrarUsuariosSql() {
        Conexion conn=new Conexion(this,"bd_roles",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')

        String insert="INSERT INTO "+ Bd.TABLA_ROLES
                +" ( " +Bd.CAMPO_ID+","+Bd.CAMPO_NOMBRE+")" +
                " VALUES ("+idrol.getText().toString()+", '"+nombrerol.getText().toString()+"')";
        db.execSQL(insert);


        //db.close();
    }
    private void limpiar() {
        idrol.setText("");
        nombrerol.setText("");
    }


   /* private void registrarUsuarios() {
        Conexion conn = new Conexion(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Bd.CAMPO_ID, campoId.getText().toString());
        values.put(Bd.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Bd.CAMPO_TELEFONO, campoTelefono.getText().toString());

        Long idResultante = db.insert(Bd.TABLA_USUARIO, Bd.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }*/
}
