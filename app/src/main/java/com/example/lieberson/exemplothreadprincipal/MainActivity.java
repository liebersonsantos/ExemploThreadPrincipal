package com.example.lieberson.exemplothreadprincipal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnBaixarImagemWeb(View view) {
        new Thread(){
            public void run(){
                try {
                    URL url = new URL("http://fotosface.com.br/fotos/imagens-4a5b1c.jpg");
                    HttpURLConnection connection;
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    final Bitmap imagem = BitmapFactory.decodeStream(input);

                    final ImageView iv = findViewById(R.id.imagem);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(imagem);
                        }
                    });
                }
                    catch(MalformedURLException e){
                        e.printStackTrace();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
