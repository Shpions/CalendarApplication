package com.dio.Service;

import com.dio.Skeleton.Event;
import com.dio.Skeleton.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ServiceInterface extends Remote {
    Event search_Event(String title) throws RemoteException;

    Event create_Event(String title, String description,
                       Date sd, Date ed, List<Person> attenders) throws RemoteException;

    ArrayList<Event> all_Event_On_Date(Person p, Date d) throws RemoteException;

    ArrayList<Event> all_Event_For_Person(Person p) throws RemoteException;

    boolean check_For_busy(Person p, Date d) throws RemoteException;

    Event find_Event_By_time(Person p, Date d) throws RemoteException;

    void add_Event(Event e) throws RemoteException;

    public boolean time_compare(Event e, Date d) throws RemoteException;

    public Event remove_Event(String title)throws RemoteException;

    Date get_Time_For_Event(Date d, List <Person> persons)throws RemoteException;

    public ArrayList<Event> searchEventByDay(Date d)throws RemoteException;

    public ArrayList<Event> searchEventByMonth(Date d)throws RemoteException;

    ArrayList<Event> showAllEvents()throws RemoteException;

    public Event addAttender(String name, Person... newPersons)throws RemoteException;
}
