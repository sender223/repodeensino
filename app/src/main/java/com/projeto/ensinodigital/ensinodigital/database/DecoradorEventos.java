package com.projeto.ensinodigital.ensinodigital.database;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Sender on 31/01/2018.
 */

public class DecoradorEventos implements DayViewDecorator {

    private final int color;
    private final HashSet<CalendarDay> dates;

    public DecoradorEventos(int color, Collection<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
    }
}

    Collection<CalendarDay> colldates; colldates = new HashSet<>(); CalendarDay eventDay2 = CalendarDay.from(2017, 07, 18); colldates.add(eventDay2); materialCalendarView.addDecorator(new EventDecorator(colldates, "Nokas")); **Alterei a Classe EventDecorator o método decorate ** view.addSpan(new String(n));

