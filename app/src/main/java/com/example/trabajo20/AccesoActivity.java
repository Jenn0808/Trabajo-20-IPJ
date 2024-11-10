package com.example.trabajo20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.nio.Buffer;

public class AccesoActivity extends AppCompatActivity {

    //variables para enlazar con los ids
    private TextView textView;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        //enlazamos con los ids
        textView= findViewById(R.id.textoCarga);
        progressBar = findViewById(R.id.barraProgreso);
        linearLayout = findViewById(R.id.layMenu);

        //crea y ejecuta el thread para simular la carga
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(600);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        //inicia el thread
        thread.start();
    }

    public void onClickMapita(View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, Mapita.class);
        //Llama al método startActivity() para iniciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }
    public void onClickRecordatorio (View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, Recordatorio.class);
        //Llama al método startActivity() parainiciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }
    public void onClickVentana(View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, VentanaActivity.class);
        //Llama al método startActivity() para iniciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }
    public void onClickVideos(View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, Videos.class);
        //Llama al método startActivity() para iniciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }
}