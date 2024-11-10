package com.example.trabajo20;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

//Importo
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;




import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Mapita extends AppCompatActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    private Marker movingMarker;
    private boolean isMoving = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapita);
        // Carga la configuración del mapa usando las preferencias predeterminadas.
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        // Obtiene la referencia al componente MapView del layout.
        MapView mapView = findViewById(R.id.mapView);
        // Establece la fuente de azulejos del mapa a MAPNIK (mapa estándar).
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        // Activa los controles de zoom en el mapa.
        mapView.setBuiltInZoomControls(true);
        // Activa el control multitáctil para el mapa (zoom con dos dedos).
        mapView.setMultiTouchControls(true);

        // Coordenadas de la tienda yeppo, Chile.
        double yeppoLatitud = -33.4277753; // Latitud de la tienda YEPPO.
        double yeppoLongitud = -70.7260617; // Longitud de la tienda YEPPO.

        // Crear objetos GeoPoint para las coordenadas definidas.
        GeoPoint yeppoPoint = new GeoPoint(yeppoLatitud, yeppoLongitud);
        // Configura la vista inicial del mapa centrada en el IP Santo Tomas con un nivel de zoom de 15.
        mapView.getController().setZoom(15.0);
        // Centra el mapa en el punto de Santiago.
        mapView.getController().setCenter(yeppoPoint);

        // Crear un marcador la tienda yeppo y Creamos un marcador en el mapa
        Marker marcadorYeppo = new Marker(mapView);
        // Establece la posición del marcador en el punto de IP Santo Tomás.
        marcadorYeppo.setPosition(yeppoPoint);
        // Establece el ancla del marcador.
        marcadorYeppo.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        // Establece el título del marcador.
        marcadorYeppo.setTitle("Tienda coreana YEPPO, Chile");
        // Establece una descripción para el marcador.
        marcadorYeppo.setSnippet("Un lugar bonito");

        // Agregar los marcadores al mapa.
        mapView.getOverlays().add(marcadorYeppo);


        // Coordenadas de fantasilandia
        double fantasilandiaLatitud =  -33.4602869; // Latitud de fantasilandia.
        double fantasilandiaLongitud = -70.6625814; // Longitud de fantasilandia.

        // Crear objetos GeoPoint para las coordenadas definidas.
        GeoPoint fantasilandiaPoint = new GeoPoint(fantasilandiaLatitud, fantasilandiaLongitud );

        // Crear un marcador para la tienda yeppo . y Creamos un marcador en el mapa
        Marker marcadorFantasilandia = new Marker(mapView);
        // Establece la posición del marcador en el punto de Yeppo.
        marcadorFantasilandia.setPosition(fantasilandiaPoint);
        // Establece el ancla del marcador.y los valores se pueden ajustar la imagen con el marcador
        marcadorFantasilandia.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        // Establece el título del marcador.
        marcadorFantasilandia.setTitle("Fantasilandia");
        // Establece una descripción para el marcador.
        marcadorFantasilandia.setSnippet("Un lugar donde se va a quitar las penas");

        marcadorFantasilandia.setIcon(getResources().getDrawable(R.drawable.gps3));


        // Agregar los marcadores al mapa.
        mapView.getOverlays().add(marcadorFantasilandia);

        // Crear una línea que conecte los 2 Marcadores
        Polyline linea = new Polyline();
        // Añade el punto del Ip y el parque a la línea.
        linea.addPoint(yeppoPoint);
        linea.addPoint(fantasilandiaPoint);
        // Establece el color de la línea (azul en formato ARGB).
        linea.setColor(0xFF0000FF);
        // Establece el ancho de la línea a 5 píxeles.
        linea.setWidth(5);
        // Añadir la línea al mapa.
        mapView.getOverlayManager().add(linea);

        // Crear el marcador de movimiento
        movingMarker = new Marker(mapView);
        movingMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        movingMarker.setIcon(getResources().getDrawable(R.drawable.gps3));
        mapView.getOverlays().add(movingMarker);

        // Iniciar el movimiento en un hilo separado
        isMoving = true;
        Thread movementThread = new Thread(() -> simulateMovement(yeppoPoint, fantasilandiaPoint, mapView));
        movementThread.start();


        // Configurar el Spinner para cambiar el tipo de mapa y Obtiene la referencia al componente Spinner del id del xml.
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        // Define un array con los tipos de mapas.
        String[] mapTypes = {"Mapa Normal", "Mapa de Transporte", "Mapa Topografico"};

        // Crear un ArrayAdapter para poblar el Spinner con los tipos de mapas.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        // Establece el layout para los elementos del Spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Asigna el adaptador al Spinner.
        mapTypeSpinner.setAdapter(adapter);

        // Listener para detectar cambios en la selección del Spinner.
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   switch (position) {
                case 0:
                    mapView.setTileSource(TileSourceFactory.MAPNIK);
                    break;
                case 1:
                    mapView.setTileSource(new XYTileSource(
                            "PublicTransport",
                            0, 18, 256, ".png", new String[]{
                            "https://tile.memomaps.de/tilegen/"}));
                    break;
                case 2:
                    mapView.setTileSource(new XYTileSource(
                            "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                            "https://a.tile.opentopomap.org/",
                            "https://b.tile.opentopomap.org/",
                            "https://c.tile.opentopomap.org/"}));
                    break;
            }
            }

            // No se hace nada cuando no se selecciona ningún elemento.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Método para simular el movimiento del marcador
    private void simulateMovement(GeoPoint startPoint, GeoPoint endPoint, MapView mapView) {
        double latDiff = (endPoint.getLatitude() - startPoint.getLatitude()) / 100.0;
        double lonDiff = (endPoint.getLongitude() - startPoint.getLongitude()) / 100.0;

        for (int i = 0; i <= 100 && isMoving; i++) {
            double newLat = startPoint.getLatitude() + (latDiff * i);
            double newLon = startPoint.getLongitude() + (lonDiff * i);
            GeoPoint newPosition = new GeoPoint(newLat, newLon);

            handler.post(() -> {
                movingMarker.setPosition(newPosition);
                mapView.invalidate();  // Actualizar el mapa
            });

            try {
                Thread.sleep(500);  // Pausa de 100 ms entre cada actualización
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isMoving = false;  // Detener el hilo de movimiento cuando la actividad se destruya
    }
}