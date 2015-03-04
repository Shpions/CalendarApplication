package com.dio.Service;

import com.dio.Event;

import java.util.*;

public class CalendarService implements RemoteInterface{

    private HashMap<String, Event> eventCollection = new HashMap<String, Event>();

    public void add_Event(Event e ){
        eventCollection.put(e.getTitle(), e);
    }

    public Event search_Event(String title){
        return eventCollection.get(title);
    }

    public Event create_Event(String title, String description,
                            Date sd, Date ed, List<String> attenders){
        Event event = new Event.Builder().setTitle(title).setDescription(description).
                setStartDate(sd).setEndDate(ed).setAttenders(attenders).build();
        return event;
    }

    public ArrayList <Event> all_Event_On_Date(String mail, Date d){
        ArrayList <Event>events = all_Event_For_Person(mail);

        ArrayList <Event> eventList = new ArrayList<Event>();
        for(Event e : events){
            if(e.getStartDate().getMonth()<= d.getMonth()&& d.getMonth() <= e.getEndDate().getMonth() &&
                    e.getStartDate().getDay() <= d.getDay() && d.getDay() <= e.getEndDate().getDay() &&
                    e.getStartDate().getYear() <= d.getYear() && d.getYear() <= e.getEndDate().getYear()){
                eventList.add(e);
            }
        }
        return eventList;
    }

    public boolean time_compare(Event e, Date d){
        boolean b = false;
        if(e.getStartDate().getTime() <= d.getTime() &&
                e.getEndDate().getTime() >= d.getTime()){
            b = true;
        }
        return b;
    }

    public void remove_Event(String title){
        eventCollection.remove(title);
    }

    public ArrayList<Event> all_Event_For_Person(String mail){
        ArrayList <Event> eventForPerson = new ArrayList<Event>();
        for(Map.Entry<String,Event> map : eventCollection.entrySet()){
            if(map.getValue().getAttenders().contains(mail)){
                eventForPerson.add(map.getValue());
            }
        }
        return eventForPerson;
    }

    public boolean check_For_busy(String mail, Date d){
        ArrayList <Event> eventList = all_Event_On_Date(mail, d);
        boolean b = false;
        for(Event e : eventList){
            if(time_compare(e, d)){
                b = true;
            }
        }
        return b;
    }

    public Event find_Event_By_time(String mail, Date d){
        ArrayList <Event> eventList = all_Event_On_Date(mail, d);
        Event event = null;
        for(Event e : eventList){
            if(time_compare(e, d)){
                    event = e;
            }
        }
        return event;
    }

    public Date get_Time_For_Event(Date d, String... mails){
        for(String mail : mails){
            while (check_For_busy(mail, d)){
                long i = d.getTime() + 60000 * 15;
                d.setTime(i);
            }
        }
        return d;
    }
}
