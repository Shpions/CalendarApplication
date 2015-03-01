package com.dio;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallendarService {

    private HashMap<String, Event> eventCollection = new HashMap<String, Event>();

    public void addEvent(Event e ){
        eventCollection.put(e.getTitle(), e);
    }

    public Event search(String title){
        Event e = null;
        for (Map.Entry<String, Event> m : eventCollection.entrySet()){
            if(m.getKey().equals(title)){
                e = m.getValue();
            }
        }
        return e;
    }

    public Event createEvent(String title, String description,
                            Date d, List<String> attenders){
        Event event = new Event.Builder().setTitle(title).setDescription(description).
                setDate(d).setAttenders(attenders).build();
        return event;
    }

}
