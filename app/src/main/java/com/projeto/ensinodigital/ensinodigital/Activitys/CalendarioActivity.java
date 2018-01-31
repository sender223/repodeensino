package com.projeto.ensinodigital.ensinodigital.Activitys;

import android.app.usage.UsageEvents;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.projeto.ensinodigital.ensinodigital.Fragments.FragmentoAgenda;
import com.projeto.ensinodigital.ensinodigital.R;
import com.projeto.ensinodigital.ensinodigital.database.DecoradorEventos;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;



public class CalendarioActivity extends AppCompatActivity {

    MaterialCalendarView calendarView;
    TextView dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarioID);
        dateDisplay = (TextView) findViewById(R.id.show_anotacoes);
        dateDisplay.setText("Agenda de Hoje: ");



        calendarView.addDecorators(new DecoradorEventos(
                getResources().getColor(R.color.colorPrimaryDark),dates));
                getResources().getColor(R.color.colorPrimaryDark);


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                FragmentoAgenda fragmentoAgenda = new FragmentoAgenda();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fr_container_id, fragmentoAgenda, fragmentoAgenda.getTag()).commit();

            }
        });

    }
}