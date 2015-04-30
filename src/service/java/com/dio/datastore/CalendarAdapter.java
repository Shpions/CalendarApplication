package com.dio.datastore;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CalendarAdapter {



    private List<EventAdapter> events;


    public CalendarAdapter() {
    }

    public List<EventAdapter> getEvents() {
        return events;
    }

    @XmlElement
    public void setEvents(List<EventAdapter> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarAdapter that = (CalendarAdapter) o;

        if (events != null ? !events.equals(that.events) : that.events != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return events != null ? events.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallendarAdapter{");
        sb.append("events=").append(events);
        sb.append('}');
        return sb.toString();
    }
}
