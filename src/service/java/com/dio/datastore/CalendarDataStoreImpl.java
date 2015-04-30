package com.dio.datastore;

import com.dio.parser.Parser;
import com.dio.parser.ParserImpl;
import com.dio.skeleton.Event;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalendarDataStoreImpl implements CalendarDataStore {
    private CalendarAdapter calendarAdapter = new CalendarAdapter();
    private Parser parser = new ParserImpl(calendarAdapter);


    private ArrayList<EventAdapter> eventAdapters;

    {
        try{
        eventAdapters = parser.showAllEvents();
        }catch (RemoteException e){
            System.out.println(e.getMessage());
        };

    }

    private final Map<String, Event> store = new HashMap<String, Event>();



    @Override
    public void publish(Event event) {
        store.put(event.getTitle(), event);
        persistEvent(event);
    }

    @Override
    public Event remove(String eventName) {
        Event event = store.get(eventName);
        store.remove(eventName);
        return event;
    }

    @Override
    public Event getEvent(String name) {
        return store.get(name);
    }

    private void persistEvent(Event expectedEvent){

        JAXBContext context = null;

        EventAdapter eventAdapter = new EventAdapter(expectedEvent);
        try {
            context = JAXBContext.newInstance(EventAdapter.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(eventAdapter, new File("./"+expectedEvent.getTitle() +". xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Event> getAllEvent(){
        ArrayList <Event> result = new ArrayList<Event>();
        for(Map.Entry<String,Event> m : store.entrySet()){
            result.add(m.getValue());
        }
        return result;
    }

}
