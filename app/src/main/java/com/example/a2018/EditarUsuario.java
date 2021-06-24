package com.example.a2018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2018.bd.Bd;
import com.example.a2018.entidades.usuario;

public class EditarUsuario extends AppCompatActivity {

   EditText campoid,camponombre,campoemail,campoapellido,campopassword;
   Conexion conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        conn=new Conexion(getApplicationContext(),"bd_usuarios",null,1);

        campoid = (EditText) findViewById(R.id.campoid);
        camponombre = (EditText) findViewById(R.id.camponombre);
        campoemail = (EditText) findViewById(R.id.campoemail);
        campoapellido = (EditText) findViewById(R.id.campoapellido);
        campopassword = (EditText) findViewById(R.id.campocontrasena);

        Bundle objetoEnviado=getIntent().getExtras();
        usuario User =null;

        if(objetoEnviado!=null){
            User= (usuario) objetoEnviado.getSerializable("Usuario");
            campoid.setText(User.getId_usuario().toString());
            campoemail.setText(User.getEmail().toString());
            camponombre.setText(User.getNombres().toString());
            campoapellido.setText(User.getApellidos().toString());
            campopassword.setText(User.getPassword().toString());

        }
    }
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnActualizar: actualizarUsuario();
                break;
            case R.id.botoneliminar: eliminarUsuario();
                break;
        }

    }

    private void actualizarUsuario(){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoid.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Bd.CAMPO_NOMBRES,campoemail.getText().toString());
        values.put(Bd.CAMPO_EMAIL,camponombre.getText().toString());
        values.put(Bd.CAMPO_APELLIDOS,campoapellido.getText().toString());
        values.put(Bd.CAMPO_PASSWORD,campopassword.getText().toString());

        db.update(Bd.TABLA_USUARIOS,values,Bd.CAMPO_ID_USUARIO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizó el usuario",Toast.LENGTH_LONG).show();
        db.close();

    }
    private void  eliminarUsuario() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoid.getText().toString()};

        db.delete(Bd.TABLA_USUARIOS, Bd.CAMPO_ID_USUARIO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó el usuario",Toast.LENGTH_LONG).show();
        campoid.setText("");
        limpiar();
        db.close();
    }
    private void limpiar() {
        campoemail.setText("");
        camponombre.setText("");
        campoapellido.setText("");
        campopassword.setText("");


    }
}