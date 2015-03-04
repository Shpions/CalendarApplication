package com.dio.Service;

import com.dio.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RemoteInterface extends Remote {
    Event search_Event(String title) throws RemoteException;
    Event create_Event(String title, String description,
                       Date sd, Date ed, List<String> attenders) throws RemoteException;
    ArrayList<Event> all_Event_On_Date(String mail, Date d) throws RemoteException;
    ArrayList<Event> all_Event_For_Person(String mail) throws RemoteException;
    boolean check_For_busy(String mail, Date d) throws RemoteException;
    Event find_Event_By_time(String mail, Date d) throws RemoteException;
    void add_Event(Event e ) throws RemoteException;
}
