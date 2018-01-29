package com.projeto.ensinodigital.ensinodigital.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.projeto.ensinodigital.ensinodigital.R;

/**
 * Created by Sender on 19/01/2018.
 */
        //para mostrar a tela de abertura após a linha abaixo temos
        //que entrar no androidmanifest.xml e colocar nossa activity la
        //para depois conseguir exibir a tela de abertura.

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abertura);
        //aqui vamos ocultar a barra actionbar na tela de abertura
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*Inserindo delay na Splash Screen com o Handler do Android
        Temos diversas formas de solucionar esse problema utilizando
        a linguagem Java, como por exemplo, pelo uso de Threads e as
        diversas APIs que a própria linguagem nos fornece.
        Entretanto, o próprio Android nos oferece a classe Handler que,
        internamente, trabalha com Threads assim como no Java, porém,
        nos provê alguns métodos capazes de nos ajudar nessa situação.
        Portanto, vamos instânciá-la:
        */
        Handler handler = new Handler();
        /*Para executarmos uma thread com delay no Handler,
        utilizamos o método postDelayed() que recebe dois parâmetros:
        1º parâmetro: uma interface Runnable que é justamente a Thread que
        será executada após o tempo de delay.
        2º parâmetro: tempo de delay em millisegundos.
        Então o nosso código fica da seguinte forma:
        */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        },5000);
    }

    private void mostrarLogin() {
        Intent intent = new Intent(SplashScreenActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
