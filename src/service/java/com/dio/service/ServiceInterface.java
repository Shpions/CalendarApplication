package com.dio.service;

import com.dio.skeleton.Event;
import com.dio.skeleton.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ServiceInterface extends Remote {

    void initEvent()throws RemoteException;

    Event searchEvent(String title) throws RemoteException;

    Event createEvent(String title, String description,
                      Date startDate, Date endDate, List<Person> attenders) throws RemoteException;

    Event editEvent(String title, String description,
                      Date startDate, Date endDate, List<Person> attenders) throws RemoteException;

    ArrayList<Event> allEventOnDate(Person person, Date date) throws RemoteException;

    ArrayList<Event> all_Event_For_Person(Person person) throws RemoteException;

    boolean checkForBusy(Person person, Date date) throws RemoteException;

    Event findEventByTime(Person person, Date date) throws RemoteException;

    void addEvent(Event event) throws RemoteException;

    boolean timeCompare(Event event, Date date) throws RemoteException;

    Event removeEvent(String title)throws RemoteException;

    Date getTimeForEvent(Date date, List<Person> persons)throws RemoteException;

    ArrayList<Event> searchEventByDay(Date date)throws RemoteException;

    ArrayList<Event> searchEventByMonth(Date date)throws RemoteException;

    ArrayList<Event> showAllEvents()throws RemoteException;

    Event addAttender(String name, Person... newPersons)throws RemoteException;
}
