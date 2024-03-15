package com.fer.aula07_animacoes;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView flecha, cortinaL, cortinaR, gohan;
    Random r;
    int anguloInicial = 0, anguloFinal = 270, qtdVoltas = 10, count = 0;
    Animation bounce, dir, esq, fade_in, fade_out, rotation, zoom_in, zoom_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flecha = findViewById(R.id.imgFlecha);
        cortinaL = findViewById(R.id.imgCortinaEsq);
        cortinaR = findViewById(R.id.imgCortinaDir);
        gohan = findViewById(R.id.imgGohan);
        bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        dir = AnimationUtils.loadAnimation(MainActivity.this, R.anim.dir_esq);
        esq = AnimationUtils.loadAnimation(MainActivity.this, R.anim.esq_dir);
        fade_in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
        rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotation);
        zoom_in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
        zoom_out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);

        r = new Random();

        //Animação da Cortina
        cortinaL.animate().translationX(-300 * 3).setDuration(3000);
        cortinaR.animate().translationX(300 * 3).setDuration(3000);

        //Animação da Seta
        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anguloFinal = anguloInicial % 360;
                anguloInicial = r.nextInt(360 * qtdVoltas) + 360;
                RotateAnimation imagem = new RotateAnimation(
                        anguloFinal, anguloInicial,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                imagem.setFillAfter(true);
                imagem.setInterpolator(new AccelerateDecelerateInterpolator());
                imagem.setDuration(3000);
                imagem.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        anguloFinal = anguloInicial % 360;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                flecha.startAnimation(imagem);
            }
        });

        //Animação via XML (Gohan)
        gohan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) gohan.startAnimation(bounce);
                else if (count == 1) gohan.startAnimation(rotation);
                else if (count == 2) gohan.startAnimation(fade_out);
                else if (count == 3) gohan.startAnimation(fade_in);
                else if (count == 4) gohan.startAnimation(zoom_in);
                else if (count == 5) gohan.startAnimation(zoom_out);
                else if (count == 6) gohan.startAnimation(dir);
                else if (count == 7) gohan.startAnimation(esq);
                count++;
                if (count > 7) count = 0;
            }
        });

    }
}