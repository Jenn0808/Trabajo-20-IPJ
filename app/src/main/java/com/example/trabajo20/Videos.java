package com.example.trabajo20;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//importo librerias nuevas de Video Web
import  android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Videos extends AccesoActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        //Aqui configuramos el video para la reproduccion local
        VideoView videoView = findViewById(R.id.videoView);
        //Construimos una URI del video
        String videoPath = "android.resource://"+ getPackageName() + "/" + R.raw.wildflower;
        //Convierto la cadena en un URI
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        //Agrego controles de reproducción del video
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        //Vinculamos el controlador de video para mostrar en la app
        mediaController.setAnchorView(videoView);
        videoView.start();


        //Configuracion del WebView para cargar video de Yotube
        WebView webView = findViewById(R.id.webView);
        //Obtengo la configuración del WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //Agrego la URL del Video en formato embed
        String videoUrl = "https://www.youtube.com/shorts/HgFhW20c-IQ";
        //Cargo el webView en la app
        webView.setWebViewClient(new WebViewClient());
        //Cargar la URL en el video del webView
        webView.loadUrl(videoUrl);


        //Configuración de reproducción del sonido MP3
        findViewById(R.id.reproducir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creo un MediaPlayer para reproducir el sonido
                MediaPlayer mediaPlayer = MediaPlayer.create(Videos.this, R.raw.mario);
                //Inicio la reproducción
                mediaPlayer.start();
                //Listener para liberar recursos cuando sonido termine
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release(); //Libero recursos cuando el sonido termine
                    }
                });
            }
        });

    }
}

