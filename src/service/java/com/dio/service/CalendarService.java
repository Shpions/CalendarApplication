package com.dio.service;

import com.dio.datastore.CalendarAdapter;
import com.dio.datastore.CalendarDataStore;
import com.dio.datastore.EventAdapter;
import com.dio.datastore.PersonAdapter;
import com.dio.parser.Parser;
import com.dio.parser.ParserImpl;
import com.dio.skeleton.Event;
import com.dio.skeleton.Person;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

public class CalendarService implements ServiceInterface {

    private static final Logger logger = Logger.getAnonymousLogger();
    private final CalendarDataStore dataStore;
    private final CalendarAdapter adapter = new CalendarAdapter();
    private final Parser parser = new ParserImpl(adapter);



    public CalendarService(CalendarDataStore dataStore) {

        this.dataStore = dataStore;
    }

    public void initEvent(){
        try {
            ArrayList<EventAdapter> adapterArrayList = parser.showAllEvents();
            for(EventAdapter eventAdapter : adapterArrayList){
                List<PersonAdapter>personAdapters = eventAdapter.getAttenders();
                List<Person> persons = new ArrayList<Person>();
                for(PersonAdapter adapter1 : personAdapters){
                    persons.add(new Person.Builder().email(adapter1.getEmail())
                            .firstName(adapter1.getFirstName()).secondName(adapter1.getSecondName())
                            .phone(adapter1.getPhone()).build());
                }

                addEvent(createEvent(eventAdapter.getTitle(), eventAdapter.getDescription(),
                        eventAdapter.getStartDate(),eventAdapter.getEndDate(),persons));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void addEvent(Event event){
        dataStore.publish(event);
        EventAdapter eventAdapter = new EventAdapter(event);
        try {
            parser.addEvent(eventAdapter);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public Event searchEvent(String title){
        return dataStore.getEvent(title);
    }

    public Event createEvent(String title, String description,
                             Date startDate, Date endDate, List<Person> attenders){
        Event event = new Event.Builder().setTitle(title).setDescription(description).
                setStartDate(startDate).setEndDate(endDate).setAttenders(attenders).build();
        return event;
    }

    public Event editEvent(String title, String description,
                             Date startDate, Date endDate, List<Person> attenders){
        Event event = null;
        if(searchEvent(title)!=null){
            removeEvent(title);
            event = new Event.Builder().setTitle(title).setDescription(description).
                setStartDate(startDate).setEndDate(endDate).setAttenders(attenders).build();
            try {
                parser.removeEvent(title);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return event;
    }

    public ArrayList <Event> allEventOnDate(Person person, Date date){
        ArrayList <Event>events = all_Event_For_Person(person);

        ArrayList <Event> eventList = new ArrayList<Event>();
        for(Event event : events){
            if(event.getStartDate().getMonth() == date.getMonth() && event.getStartDate().getDay() == date.getDay()
                    && event.getStartDate().getYear() == date.getYear() ||
                    event.getEndDate().getMonth() == date.getMonth() && event.getEndDate().getDay() == date.getDay()
                            && event.getEndDate().getYear() == date.getYear()){
                eventList.add(event);
            }
        }
        return eventList;
    }

    public boolean timeCompare(Event event, Date date){
        boolean b = false;
        if(event.getStartDate().getTime() <= date.getTime() &&
                event.getEndDate().getTime() >= date.getTime()){
            b = true;
        }
        return b;
    }

    public Event removeEvent(String title){
        EventAdapter eventAdapter = new EventAdapter(searchEvent(title));
        try {
            parser.removeEvent(eventAdapter.getTitle());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return dataStore.remove(title);
    }

    public ArrayList<Event> all_Event_For_Person(Person person){
        ArrayList <Event> eventForPerson = new ArrayList<Event>();
        for(Event event : dataStore.getAllEvent()){
            if(event.getAttenders().contains(person)){
                eventForPerson.add(event);
            }
        }
        return eventForPerson;
    }

    public boolean checkForBusy(Person person, Date date){

        ArrayList<Event> eventList = allEventOnDate(person, date);
        boolean b = false;
        for(Event event : eventList){
            if(timeCompare(event, date)){
                b = true;
            }
        }
        return b;
    }

    public Event findEventByTime(Person person, Date date){
        ArrayList<Event> eventList = allEventOnDate(person, date);
        Event theChosenOneEvent = null;
        for(Event event : eventList){
            if(timeCompare(event, date)){
                    theChosenOneEvent = event;
            }
        }
        return theChosenOneEvent;
    }

    public Date getTimeForEvent(Date date, List<Person> persons){
        Date [] dates = new Date[persons.size()];
        int count = 0;
        for(Person person : persons){
            while (checkForBusy(person, date)){
                long i = date.getTime() + 60000 * 15;
                date.setTime(i);
                dates[count] = date;
                }
            count++;
        }
        if(dates[0] == null){
            dates[0] = date;
        }

        for(int i = 0; i < dates.length; i++){

            if(dates[i] == null && i>0){
                dates[i] = dates[i-1];
            }
            if(dates[0] != dates[i]){
                getTimeForEvent(dates[i], persons);
            }
        }
        return dates[dates.length-1];
    }

    public ArrayList<Event> searchEventByDay(Date date){
        ArrayList<Event> eventsList = new ArrayList<Event>();
        for(Event event : dataStore.getAllEvent()){
            if(event.getStartDate().getDay() == date.getDay() &&
                    event.getStartDate().getMonth() == date.getMonth() ||
                    event.getEndDate().getDay() == date.getDay() &&
                            event.getEndDate().getMonth() == date.getMonth()){
                eventsList.add(event);}
        }
        return eventsList;
    }

    public ArrayList<Event> searchEventByMonth(Date date){
        ArrayList<Event> eventsList = new ArrayList<Event>();
        for(Event event : dataStore.getAllEvent()){
            if(event.getStartDate().getMonth() == date.getMonth() ||
                    event.getEndDate().getMonth() == date.getMonth()){
                eventsList.add(event);}
        }
        return eventsList;
    }

    public ArrayList<Event> showAllEvents(){
        return dataStore.getAllEvent();
    }

    public Event addAttender(String name, Person... newPersons) {

        Event originEvent = dataStore.remove(name);
        if (originEvent == null) {
            return null;
        }
        List<Person> newAttenders = new ArrayList<Person>(originEvent.getAttenders());
        newAttenders.addAll(asList(newPersons));

        Event newEvent = new Event.Builder(originEvent)
                .setAttenders(newAttenders)
                .build();
        dataStore.publish(newEvent
        );
        return newEvent;
    }
}
