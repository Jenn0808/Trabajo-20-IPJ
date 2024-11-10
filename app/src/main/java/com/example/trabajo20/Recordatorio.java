package com.example.trabajo20;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Recordatorio extends AppCompatActivity {

    Spinner spDia;
    Spinner spMes;
    String[] dia = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] mes = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    EditText edtTarea, edtDes;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordatorio);

        //campos
        edtTarea = (EditText) findViewById(R.id.edtRegistro);
        edtDes = (EditText) findViewById(R.id.edtDes);
        spDia = (Spinner) findViewById(R.id.spDia);
        spMes = (Spinner) findViewById(R.id.spMes);
        lista = (ListView) findViewById(R.id.lstLista);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dia);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDia.setAdapter(spinnerAdapter);
        ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mes);
        spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMes.setAdapter(spinnerAdapter1);
        CargarLista();
    }

    public void CargarLista() {
        DBHealper dh = new DBHealper(this, "recordatorio.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("Select tarea, descripcion, dia, mes from recordatorio", null);
        String[] arr = new String[c.getCount()];
        if (c.moveToFirst() == true) {
            int i = 0;
            do {
                String linea = c.getString(0) + "  |  " + c.getString(1)
                        + "  Fecha: " + c.getString(2) + " / " + c.getString(3) ;
                arr[i] = linea;
                i++;
            } while (c.moveToNext() == true);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_expandable_list_item_1, arr);
            lista.setAdapter(adapter);
            bd.close();
        }
    }

    public void onClickAgregar (View view){
        DBHealper dh=new DBHealper(this, "recordatorio.db", null, 1);
        SQLiteDatabase bd= dh.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("tarea", edtTarea.getText().toString());
        reg.put("descripcion", edtDes.getText().toString());
        reg.put("dia", spDia.getSelectedItem().toString());
        reg.put("mes", spMes.getSelectedItem().toString());
        long resp = bd.insert("recordatorio", null, reg);
        bd.close();
        if(resp==-1){
            Toast.makeText(this,"No se puede ingresar la tarea", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Registro Agregado", Toast.LENGTH_LONG).show();
        }
        CargarLista();
        limpiar();
    }
    public void limpiar(){
        edtTarea.setText("");
        edtDes.setText("");
    }

    public void onClickModificar(View view){
        //Conexion a la BDD para manipular los registros
        DBHealper dh = new DBHealper(this, "recordatorio.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //Envio los datos a modificar
        reg.put("tarea", edtTarea.getText().toString());
        reg.put("descripcion", edtDes.getText().toString());
        reg.put("dia", spDia.getSelectedItem().toString());
        reg.put("mes", spMes.getSelectedItem().toString());
        //Actualizo el registro de la BBD por el RUT
        long respuesta = bd.update("recordatorio", reg, "tarea=?", new String[]{edtTarea.getText().toString()});
        bd.close();
        //Envio la respuesta al usuario
        if (respuesta == -1){
            Toast.makeText(this,"Dato No Modificado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Dato Modificado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }

    public void onClickEliminar(View view) {
        // Conecto la BDD para eliminar un registro de la tabla recordatorio
        DBHealper dh = new DBHealper(this, "recordatorio.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();

        // Ingreso el texto de la tarea a eliminar desde el EditText
        String TareaEliminar = edtTarea.getText().toString();

        // Verifico que el EditText no esté vacío antes de intentar eliminar
        if (TareaEliminar.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa una tarea para eliminar", Toast.LENGTH_SHORT).show();
            return; // Si el campo está vacío, salimos del método
        }

        try {
            // Query para eliminar el registro usando parámetros para evitar problemas de inyección SQL
            long respuesta = bd.delete("recordatorio", "tarea = ?", new String[]{TareaEliminar});

            // Verifico si la eliminación fue exitosa
            if (respuesta == 0) {
                Toast.makeText(this, "No se encontró la tarea para eliminar", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Tarea eliminada con éxito", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al eliminar la tarea", Toast.LENGTH_LONG).show();
        } finally {
            // Aseguramos cerrar la base de datos después de la operación
            bd.close();
        }

        // Limpiamos el EditText y recargamos la lista de tareas
        limpiar();
        CargarLista();
    }


    public void onClickAtras(View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, AccesoActivity.class);
        //Llama al método startActivity() para iniciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }
    public void onClickTareas(View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, Recordatorio.class);
        //Llama al método startActivity() para iniciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }
}