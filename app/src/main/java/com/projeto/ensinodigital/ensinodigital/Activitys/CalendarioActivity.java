package com.projeto.ensinodigital.ensinodigital.Activitys;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.projeto.ensinodigital.ensinodigital.Fragments.FragmentoAgenda;
import com.projeto.ensinodigital.ensinodigital.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;


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

        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2017, 1, 1))
                .setMaximumDate(CalendarDay.from(2020, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        

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