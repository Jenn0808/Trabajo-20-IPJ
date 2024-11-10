package com.example.evaluacion1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VentanaActivity extends AccesoActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana);
    }

    public void onClickVentana(View view) {
        Intent intent = new Intent(this, VentanaActivitytwo.class);
        startActivity(intent);
    }
}
