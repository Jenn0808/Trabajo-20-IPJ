package com.example.evaluacion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaramos atributos

    private EditText usuarioEditText;
    private EditText contasenaText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazar los atributos con los ids de la vista main

        usuarioEditText = findViewById(R.id.usuario);
        contasenaText = findViewById(R.id.contrasena);
        spinner = findViewById(R.id.spinnerRoles);

        //creo un arreglo con los elementos del spinner

        String[] roles = {"Administrador", "Usuario"};

        // creamos un arrayAdapter para poblar el

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        //asigno el arrayadapter al spinner

        spinner.setAdapter(adapter);

    }
    //programamos un metodo onClick para ir vista
    //dependiendo de un usuario especifico


    public void  onClickIngresar(View view){
        //declaramos variables para obtener los valores ingresados
        //por el ususario
        String user = usuarioEditText.getText().toString().trim();
        String pass = contasenaText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();

        //si el campo esta vacio  salta un alerta
        if (user.isEmpty()){
            Toast.makeText(this, "Credencianles Incorrectas", Toast.LENGTH_SHORT).show();
            return;
        }

        if (user.equals("usuario1") && pass.equals("1234") && rol.equals("Usuario"))
        {

            Intent intent = new Intent(this, AccesoActivity.class);
            startActivity(intent);
        }else if (user.equals("usuario2") && pass.equals("1234") && rol.equals("Administrador"))
        {
            Intent intent = new Intent(this, AccesoActivity.class);
            startActivity(intent);
        }else {
            //crear alerta de credenciales al no colocar nada o equivocarse
            Toast.makeText(this, "Credencianles Incorrectas", Toast.LENGTH_SHORT).show();
        }

    }
}