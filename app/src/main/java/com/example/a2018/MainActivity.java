package com.example.a2018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2018.bd.Bd;
import com.example.a2018.entidades.rol;
import com.example.a2018.entidades.usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnagregar, btnbuscar;
    EditText campoapellido2;
    Spinner combousuario;

    ArrayList<String> listaPersonas;
    ArrayList<rol> personasList;
    ListView listViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<usuario> listaUsuarios;

    Conexion conn,conne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnagregar = (Button) findViewById(R.id.btnagregar);
        btnbuscar = (Button) findViewById(R.id.btnbuscar);
        campoapellido2 = (EditText) findViewById(R.id.campoapellido2);
        combousuario = (Spinner) findViewById(R.id.comborol);

        conn = new Conexion(getApplicationContext(), "bd_usuarios", null, 1);
        conne=new Conexion(getApplicationContext(),"bd_roles",null,1);

        listViewUsuarios = (ListView) findViewById(R.id.listViewUsuarios);

        ListaUsuarios();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewUsuarios.setAdapter(adaptador);

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion = "Id: " + listaUsuarios.get(pos).getId_usuario() + "\n";
                informacion += "Email: " + listaUsuarios.get(pos).getEmail() + "\n";
                informacion += "Nombre: " + listaUsuarios.get(pos).getNombres() + "\n";
                informacion += "Apellido: " + listaUsuarios.get(pos).getApellidos() + "\n";
                informacion += "Contraseña: " + listaUsuarios.get(pos).getPassword() + "\n";

                // Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                usuario User = listaUsuarios.get(pos);

                Intent intent = new Intent(MainActivity.this, EditarUsuario.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Usuario", User);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptadores=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);

        combousuario.setAdapter(adaptadores);



        combousuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conne.getReadableDatabase();

        rol persona=null;
        personasList =new ArrayList<rol>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Bd.TABLA_ROLES,null);

        while (cursor.moveToNext()){
            persona=new rol();
            persona.setId(cursor.getInt(0));
            persona.setNombres1(cursor.getString(1));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombres1());


            personasList.add(persona);

        }
        obtenerListas();
    }

    private void obtenerListas() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombres1());
        }

    }

    private void ListaUsuarios() {
        SQLiteDatabase db = conn.getReadableDatabase();

        usuario usuario = null;
        listaUsuarios = new ArrayList<usuario>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM " + Bd.TABLA_USUARIOS, null);

        while (cursor.moveToNext()) {
            usuario = new usuario();
            usuario.setId_usuario(cursor.getInt(0));
            usuario.setEmail(cursor.getString(2));
            usuario.setNombres(cursor.getString(1));
            usuario.setApellidos(cursor.getString(3));
            usuario.setPassword(cursor.getString(4));

            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            listaInformacion.add(listaUsuarios.get(i).getId_usuario() + " - "
                    + listaUsuarios.get(i).getNombres());
        }

    }


    public void onButtonClick1(View view) {
        Intent intent = new Intent(MainActivity.this, Consultar.class);
        startActivity(intent);
    }


    public void onButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, AgregarUsuario.class);
        startActivity(intent);

    }

}