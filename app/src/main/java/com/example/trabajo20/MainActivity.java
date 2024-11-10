package com.example.trabajo20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contrasena);
        spinner = findViewById(R.id.spinnerRoles);
        String[] roles = {"Administrador", "Usuario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void onClickAcceder(View view) {
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();
        if (user.isEmpty()) {
            Toast.makeText(this, "El campo de usuario esta vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            Toast.makeText(this, "El campo de contraseña esta vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user.equals("usuario1") && pass.equals("1234") && rol.equals("Usuario")) {
            Intent intent = new Intent(this, AccesoActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
        }
        if (user.equals("admi1") && pass.equals("1234") && rol.equals("Administrador")) {
            Intent intent = new Intent(this, AccesoActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}
