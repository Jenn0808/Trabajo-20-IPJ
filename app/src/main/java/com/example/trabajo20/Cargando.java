package com.example.trabajo20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Cargando extends AccesoActivity {
    //variables para enlazar con los ids
    private TextView textView;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cargando);

        //enlazamos con los ids
        textView = findViewById(R.id.textoCarga);
        progressBar = findViewById(R.id.barraProgreso);
        linearLayout = findViewById(R.id.layMenu);

        //crea y ejecuta el thread para simular la carga
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
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
}
