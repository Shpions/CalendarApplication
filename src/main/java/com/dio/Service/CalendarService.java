package com.dio.Service;

import com.dio.DataStore.CalendarDataStore;
import com.dio.Skeleton.Event;
import com.dio.Skeleton.Person;

import java.util.*;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

public class CalendarService implements ServiceInterface {

    //local code review (vtegza): not used, should be private @ 09.03.15
    public static final Logger logger = Logger.getAnonymousLogger();
    private final CalendarDataStore dataStore;

    public CalendarService(CalendarDataStore dataStore) {

        this.dataStore = dataStore;
    }

    //local code review (vtegza): name should be given in camelCase @ 09.03.15
    public void add_Event(Event e ){
        dataStore.publish(e);
    }

    public Event search_Event(String title){
        return dataStore.getEvent(title);
    }

    public Event create_Event(String title, String description,
                            Date sd, Date ed, List<Person> attenders){
        Event event = new Event.Builder().setTitle(title).setDescription(description).
                setStartDate(sd).setEndDate(ed).setAttenders(attenders).build();
        return event;
    }

    public ArrayList <Event> all_Event_On_Date(Person p, Date d){
        ArrayList <Event>events = all_Event_For_Person(p);

        ArrayList <Event> eventList = new ArrayList<Event>();
        //local code review (vtegza): give readable names for every variable @ 09.03.15
        for(Event e : events){
            if(e.getStartDate().getMonth() == d.getMonth() && e.getStartDate().getDay() == d.getDay()
                    && e.getStartDate().getYear() == d.getYear() ||
                    e.getEndDate().getMonth() == d.getMonth() && e.getEndDate().getDay() == d.getDay()
                            && e.getEndDate().getYear() == d.getYear()){
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

    public Event remove_Event(String title){
        return dataStore.remove(title);
    }

    public ArrayList<Event> all_Event_For_Person(Person p){
        ArrayList <Event> eventForPerson = new ArrayList<Event>();
        for(Event e : dataStore.getAllEvent()){
            if(e.getAttenders().contains(p)){
                eventForPerson.add(e);
            }
        }
        return eventForPerson;
    }

    public boolean check_For_busy(Person p, Date d){

        ArrayList<Event> eventList = all_Event_On_Date(p, d);
        boolean b = false;
        for(Event e : eventList){
            if(time_compare(e, d)){
                b = true;
            }
        }
        return b;
    }

    public Event find_Event_By_time(Person p, Date d){
        ArrayList<Event> eventList = all_Event_On_Date(p, d);
        Event event = null;
        for(Event e : eventList){
            if(time_compare(e, d)){
                    event = e;
            }
        }
        return event;
    }

    public Date get_Time_For_Event(Date d, List <Person> persons){
        Date [] dates = new Date[persons.size()];
        int count = 0;
        for(Person person : persons){
            while (check_For_busy(person, d)){
                long i = d.getTime() + 60000 * 15;
                d.setTime(i);
                dates[count] = d;
                }
            count++;
        }
        for(int i = 0; i < dates.length; i++){
            if(dates[i] == null && i>0){
                dates[i] = dates[i-1];
            }
            if(dates[0] != dates[i]){
                get_Time_For_Event(dates[i], persons);
            }
        }
        return dates[dates.length-1];
    }

    public ArrayList<Event> searchEventByDay(Date d){
        ArrayList<Event> eventsList = new ArrayList<Event>();
        for(Event e : dataStore.getAllEvent()){
            if(e.getStartDate().getDay() == d.getDay() &&
                    e.getStartDate().getMonth() == d.getMonth() ||
                    e.getEndDate().getDay() == d.getDay() &&
                            e.getEndDate().getMonth() == d.getMonth()){
                eventsList.add(e);}
        }
        return eventsList;
    }

    public ArrayList<Event> searchEventByMonth(Date d){
        ArrayList<Event> eventsList = new ArrayList<Event>();
        for(Event e : dataStore.getAllEvent()){
            if(e.getStartDate().getMonth() == d.getMonth() ||
                    e.getEndDate().getMonth() == d.getMonth()){
                eventsList.add(e);}
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
