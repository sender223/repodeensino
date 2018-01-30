package com.projeto.ensinodigital.ensinodigital.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.projeto.ensinodigital.ensinodigital.R;

public class ActivityAnotacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anotacoes);
        final CalendarView datePicker = (CalendarView) findViewById(R.id.calendarioAnotacoesId);
        Button button = (Button) findViewById(R.id.salvarAnotacoes);
        final EditText noteEditText = (EditText) findViewById(R.id.textoEdicaoId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                MyEventDay myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.ic_message_black_48dp, noteEditText.getText().toString());
                returnIntent.putExtra(MainActivity.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}