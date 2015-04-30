package com.dio.parser;

import com.dio.datastore.EventAdapter;
import com.dio.datastore.PersonAdapter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface Parser extends Remote{


    EventAdapter createEvent(String title, String description,
                      Date startDate, Date endDate, List<PersonAdapter> attenders) throws RemoteException;

    void addEvent(EventAdapter eventAdapter) throws RemoteException;

    EventAdapter removeEvent(String title)throws RemoteException;

    EventAdapter editEvent(String title, String description, Date startDate, Date endDate,
                   List<PersonAdapter> attenders)throws RemoteException;

    ArrayList<EventAdapter> showAllEvents()throws RemoteException;
}
