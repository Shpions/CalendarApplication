package com.dio.datastore;

import com.dio.skeleton.Event;
import com.dio.skeleton.Person;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class CalendarDataStoreImplTest {

    @Test
    public void testAddEvent() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        Date inputEndDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        List<Person> attenders = Arrays.asList(new Person.Builder().firstName("aName").build());

        Event expectedEvent = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();

        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore =new CalendarDataStoreImpl();

        // invoke method on class to test
        dataStore.publish(expectedEvent);

        // assert return value
        Event returnedValue = dataStore.getEvent(inputName);

        assertEquals(expectedEvent, returnedValue);

        // verify mock expectations
    }

    @Test
    public void testRemove() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        Date inputEndDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        List<Person> attenders = Arrays.asList(new Person.Builder().firstName("aName").build());

        Event expectedEvent = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();

        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore =new CalendarDataStoreImpl();
        dataStore.publish(expectedEvent);

        // invoke method on class to test
        Event returnedValue = dataStore.remove(inputName);

        // assert return value
        Event returnedEmptyValue = dataStore.remove(inputName);

        assertEquals(expectedEvent, returnedValue);
        assertNull(returnedEmptyValue);

        // verify mock expectations
    }

    @Test
    public void testGetEvent() throws Exception {
        Date inputStartDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        Date inputEndDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        List<Person> attenders = Arrays.asList(new Person.Builder().firstName("aName").build());

        Event expectedEvent = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();
        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore =new CalendarDataStoreImpl();
        dataStore.publish(expectedEvent);

        assertEquals(dataStore.getEvent(inputName), expectedEvent);
        // verify mock expectations
    }

    public void testGetAllEvents() throws Exception {
        Date inputStartDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        Date inputEndDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        List<Person> attenders = Arrays.asList(new Person.Builder().firstName("aName").build());

        Event expectedEvent = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();
        Event expectedEvent2 = new Event.Builder()
                .setTitle("secound")
                .setDescription("secound event")
                .setStartDate(new Date(2015,6,13,11,32))
                .setEndDate(new Date(2015,6,13,15,40))
                .setAttenders(attenders)
                .build();
        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore =new CalendarDataStoreImpl();
        dataStore.publish(expectedEvent);
        dataStore.publish(expectedEvent2);

        ArrayList<Event> e = new ArrayList<Event>();
        e.add(expectedEvent);
        e.add(expectedEvent2);
        assertEquals(dataStore.getAllEvent(), e);
        // verify mock expectations
    }

}
