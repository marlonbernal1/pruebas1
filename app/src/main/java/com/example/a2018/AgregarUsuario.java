package com.example.a2018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2018.bd.Bd;
import com.example.a2018.entidades.rol;

import java.util.ArrayList;

public class AgregarUsuario extends AppCompatActivity {
    EditText campoid1,campoemail,camponombre,campoapellido,campocontrasena;
    Spinner comboproducto;

    ArrayList<String> listaPersonas;
    ArrayList<rol> personasList;

    Conexion conn, conne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        campoid1=(EditText) findViewById(R.id.campoid);
        campoemail=(EditText) findViewById(R.id.campoemail);
        camponombre=(EditText) findViewById(R.id.camponombre);
        campoapellido=(EditText) findViewById(R.id.campoapellido);
        campocontrasena=(EditText) findViewById(R.id.campocontrasena);
        comboproducto=(Spinner) findViewById(R.id.comborol1);

        conne=new Conexion(getApplicationContext(),"bd_roles",null,1);
        conn=new Conexion(getApplicationContext(),"bd_usuarios",null,1);
        consultarListaPersonas();
        limpiar();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaPersonas);

        comboproducto.setAdapter(adaptador);



        comboproducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void registrarMascota() {

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Bd.CAMPO_ID_USUARIO,campoid1.getText().toString());
        values.put(Bd.CAMPO_NOMBRES,campoemail.getText().toString());
        values.put(Bd.CAMPO_EMAIL,camponombre.getText().toString());
        values.put(Bd.CAMPO_APELLIDOS,campoapellido.getText().toString());
        values.put(Bd.CAMPO_PASSWORD,campocontrasena.getText().toString());

        int idCombo= (int) comboproducto.getSelectedItemId();
        /**
         * Valida la seleccion del combo de dueños, si el usuario elige "seleccione" entonces
         * se retorna el id 0 ya que la palabra "seleccione" se encuentra en la pos 0 del combo,
         * sinó entonces se retorna la posicion del combo para consultar el usuario almacenado en la lista
         */
        if (idCombo!=0){
            Log.i("TAMAÑO",personasList.size()+"");
            Log.i("id combo",idCombo+"");
            Log.i("id combo - 1",(idCombo-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
            int idrol=personasList.get(idCombo-1).getId();
            Log.i("id DUEÑO",idrol+"");

            values.put(Bd.CAMPO_ROL,idrol);

            Long idResultante=db.insert(Bd.TABLA_USUARIOS,Bd.CAMPO_ID_USUARIO,values);

            Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante, Toast.LENGTH_SHORT).show();
            db.close();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar un Dueño",Toast.LENGTH_LONG).show();
        }


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


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnguardar: registrarMascota();
        }
    }
    private void limpiar() {
        camponombre.setText("");
        campoemail.setText("");
        campoapellido.setText("");
        campocontrasena.setText("");


    }


}