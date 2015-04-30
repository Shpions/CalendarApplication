package com.dio.service;

import com.dio.datastore.CalendarDataStore;
import com.dio.datastore.CalendarDataStoreImpl;
import com.dio.skeleton.Event;
import com.dio.skeleton.Person;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CalendarServiceImplTest {

    @Test
    public void testRemoveEvent() throws Exception {
        // initialize variable inputs
        String inputName = "sampleEvent";

        Event expectedEvent = new Event.Builder()
                .setTitle(inputName)
                .build();

        // initialize mocks
        CalendarDataStore dataStore = mock(CalendarDataStore.class);
        when(dataStore.remove(inputName))
                .thenReturn(expectedEvent);

        // initialize class to test
        ServiceInterface service = new CalendarService(dataStore);

        // invoke method on class to test
        Event returnedValue = service.removeEvent(inputName);

        // assert return value
        assertEquals(expectedEvent, returnedValue);
    }

    @Test
    public void testGetEvent() throws Exception {
        // initialize variable inputs
        String inputName = "sampleEvent";

        Event expectedEvent = new Event.Builder()
                .setTitle(inputName)
                .build();

        // initialize mocks
        CalendarDataStore dataStore = mock(CalendarDataStore.class);
        when(dataStore.getEvent(inputName)).thenReturn(expectedEvent);


        // initialize class to test
        ServiceInterface service = new CalendarService(dataStore);

        // invoke method on class to test
        Event returnedValue = service.searchEvent(inputName);

        // assert return value
        assertEquals(expectedEvent, returnedValue);
    }

    @Test
    public void testAddAttenderWithNull() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        Date inputEndDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        Person inputPerson = new Person.Builder().firstName("aName").build();
        Person inputNewPerson = new Person.Builder().firstName("aName2").build();
        List<Person> attenders = Arrays.asList(inputPerson);
        List<Person> newAttenders = Arrays.asList(inputPerson,inputNewPerson);


        Event expectedNewEvent = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();


        // initialize mocks
        CalendarDataStore dataStore = mock(CalendarDataStore.class);
        when(dataStore.remove(inputName)).thenReturn(null);


        // initialize class to test
        ServiceInterface service = new CalendarService(dataStore);

        // invoke method on class to test
        Event returnValue = service.addAttender(inputName, inputNewPerson);

        // assert return value
        assertNull(returnValue);

        // verify mock expectations
        verify(dataStore,times(0)).publish(expectedNewEvent);
    }

    @Test
    public void test_all_Event_On_Date() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, 6,8,10, 10);
        Date inputEndDate = new Date(2008, 6,8,11, 12);
        Date inputStartDate2 = new Date(2008, 6,8,12, 12);
        Date inputEndDate2 = new Date(2008, 6,8,13, 42);

        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        Person p1 = new Person.Builder().email("xxii@ukr.net").firstName("Stas").phone("0502622433").secondName("Grin").build();
        Person p2 = new Person.Builder().email("pens@yahoo.com").firstName("Alex").phone("0503949454").secondName("Penskiy").build();
        Person p3 = new Person.Builder().email("ksu@ukr.net").firstName("Ksu").phone("093").secondName("Havr").build();
        ArrayList<Person> attenders = new ArrayList<Person>();
        attenders.add(p1);attenders.add(p2);attenders.add(p3);

        Event exEvent1 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();
        Event exEvent2 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate2)
                .setEndDate(inputEndDate2)
                .setAttenders(attenders)
                .build();
        ArrayList <Event> list = new ArrayList<Event>();
        list.add(exEvent1);list.add(exEvent2);

        // initialize mocks
        ServiceInterface service = mock(CalendarService.class);


        // invoke method on class to test
        when(service.allEventOnDate(p1, new Date(2008, 6, 8, 10, 10))).thenReturn(list);

        ArrayList <Event> returnValue = service.allEventOnDate(p1, new Date(2008, 6, 8, 10, 10));
        ArrayList <Event> expValue = list;

        // assert return value
       assertEquals(returnValue, expValue);
    }

    @Test
    public void test_time_compare() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, 6,8,10, 10);
        Date inputEndDate = new Date(2008, 6,8,11, 12);
        Date inputStartDate2 = new Date(2008, 6,8,12, 12);
        Date inputEndDate2 = new Date(2008, 6,8,13, 42);

        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        Person p1 = new Person.Builder().email("xxii@ukr.net").firstName("Stas").phone("0502622433").secondName("Grin").build();
        Person p2 = new Person.Builder().email("pens@yahoo.com").firstName("Alex").phone("0503949454").secondName("Penskiy").build();
        Person p3 = new Person.Builder().email("ksu@ukr.net").firstName("Ksu").phone("093").secondName("Havr").build();
        ArrayList<Person> attenders = new ArrayList<Person>();
        attenders.add(p1);attenders.add(p2);attenders.add(p3);

        Event exEvent1 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();
        Event exEvent2 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate2)
                .setEndDate(inputEndDate2)
                .setAttenders(attenders)
                .build();


        // initialize mocks
        ServiceInterface service = mock(CalendarService.class);


        // invoke method on class to test
         when(service.timeCompare(exEvent1, new Date(2008, 6, 8, 10, 10)))
                .thenReturn(true);
        boolean b = service.timeCompare(exEvent1, new Date(2008, 6, 8, 10, 10));


        // assert return value
        assertEquals(b, true);
    }

    @Test
    public void test_check_For_busy() throws Exception {
        // initialize variable inputs
        Person p1 = new Person.Builder().email("xxii@ukr.net").firstName("Stas").phone("0502622433").secondName("Grin").build();
        // initialize mocks
        ServiceInterface service = mock(CalendarService.class);

        // invoke method on class to test
        when(service.checkForBusy(p1, new Date(2015, 3, 4, 12, 00))).thenReturn(false);

        boolean res = service.checkForBusy(p1, new Date(2015, 3, 4, 12, 00));


        // assert return value
        assertEquals(false, res);
    }

    @Test
    public void test_find_Event_By_time() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, 6,8,10, 10);
        Date inputEndDate = new Date(2008, 6,8,11, 12);
        Date inputStartDate2 = new Date(2008, 6,8,12, 12);
        Date inputEndDate2 = new Date(2008, 6,8,13, 42);

        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        Person p1 = new Person.Builder().email("xxii@ukr.net").firstName("Stas").phone("0502622433").secondName("Grin").build();
        Person p2 = new Person.Builder().email("pens@yahoo.com").firstName("Alex").phone("0503949454").secondName("Penskiy").build();
        Person p3 = new Person.Builder().email("ksu@ukr.net").firstName("Ksu").phone("093").secondName("Havr").build();
        ArrayList<Person> attenders = new ArrayList<Person>();
        attenders.add(p1);attenders.add(p2);attenders.add(p3);

        Event exEvent1 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();
        Event exEvent2 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate2)
                .setEndDate(inputEndDate2)
                .setAttenders(attenders)
                .build();


        // initialize mocks
        ServiceInterface service = mock(CalendarService.class);

        // invoke method on class to test
        when(service.findEventByTime(p1, new Date(2008, 6, 8, 10, 10))).thenReturn(exEvent1);

        Event e = service.findEventByTime(p1, new Date(2008, 6, 8, 10, 10));

        // assert return value
        assertEquals(e, exEvent1);
    }


    @Test
    public void test_searchEventByDay() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, Calendar.APRIL,5,10, 00);
        Date inputEndDate = new Date(2008, Calendar.APRIL,5,11, 12);
        Date inputStartDate2 = new Date(2008, Calendar.APRIL,5,11, 15);
        Date inputEndDate2 = new Date(2008, Calendar.APRIL,5,16, 45);

        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        Person p1 = new Person.Builder().email("xxii@ukr.net").firstName("Stas").phone("0502622433").secondName("Grin").build();
        Person p2 = new Person.Builder().email("pens@yahoo.com").firstName("Alex").phone("0503949454").secondName("Penskiy").build();
        Person p3 = new Person.Builder().email("ksu@ukr.net").firstName("Ksu").phone("093").secondName("Havr").build();
        ArrayList<Person> attenders = new ArrayList<Person>();
        attenders.add(p1);attenders.add(p2);attenders.add(p3);

        Event exEvent1 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();
        Event exEvent2 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate2)
                .setEndDate(inputEndDate2)
                .setAttenders(attenders)
                .build();


        // initialize mocks
        ServiceInterface service = mock(CalendarService.class);

        // invoke method on class to test
        when(service.searchEventByDay(new Date(2005, Calendar.APRIL, 5))).thenReturn(new ArrayList<Event>());
        ArrayList<Event> result  = service.searchEventByDay(new Date(2005, Calendar.APRIL, 5));
        ArrayList<Event> expValue = new ArrayList<Event>();


        // assert return value
        assertEquals(result, expValue);
    }

    @Test
    public void test_searchEventByMonth() throws Exception {
        // initialize variable inputs
        Date inputStartDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,10, 00);
        Date inputEndDate = new Date(2008, Calendar.APRIL,Calendar.TUESDAY,11, 12);
        Date inputStartDate2 = new Date(2008, Calendar.APRIL,Calendar.WEDNESDAY,11, 15);
        Date inputEndDate2 = new Date(2008, Calendar.APRIL,Calendar.WEDNESDAY,16, 45);

        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        Person p1 = new Person.Builder().email("xxii@ukr.net").firstName("Stas").phone("0502622433").secondName("Grin").build();
        Person p2 = new Person.Builder().email("pens@yahoo.com").firstName("Alex").phone("0503949454").secondName("Penskiy").build();
        Person p3 = new Person.Builder().email("ksu@ukr.net").firstName("Ksu").phone("093").secondName("Havr").build();
        ArrayList<Person> attenders = new ArrayList<Person>();
        attenders.add(p1);attenders.add(p2);attenders.add(p3);

        Event exEvent1 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate)
                .setEndDate(inputEndDate)
                .setAttenders(attenders)
                .build();
        Event exEvent2 = new Event.Builder()
                .setTitle(inputName)
                .setDescription(inputDescription)
                .setStartDate(inputStartDate2)
                .setEndDate(inputEndDate2)
                .setAttenders(attenders)
                .build();


        // initialize mocks
        CalendarDataStore data = mock(CalendarDataStoreImpl.class);
        ServiceInterface service = new CalendarService(data);
        data.publish(exEvent1); data.publish(exEvent2);

        // invoke method on class to test
        when(service.searchEventByMonth(new Date(2005, Calendar.APRIL, 5))).thenReturn(new ArrayList<Event>());
        ArrayList<Event> result = service.searchEventByMonth(new Date(2005, Calendar.JULY, 5));
        ArrayList<Event> expValue = new ArrayList<Event>();


        // assert return value
        assertEquals(result, expValue);
    }
}
