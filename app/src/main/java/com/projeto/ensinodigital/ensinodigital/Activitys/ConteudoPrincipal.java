package com.projeto.ensinodigital.ensinodigital.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.projeto.ensinodigital.ensinodigital.R;

/**
 * Created by Sender on 24/01/2018.
 */

public class ConteudoPrincipal extends AppCompatActivity {

    private Button agenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        agenda = (Button) findViewById(R.id.agendaId);

        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgenda = new Intent(ConteudoPrincipal.this, AgendaActivity.class);
                startActivity(intentAgenda);

            }
        });

    }

}

