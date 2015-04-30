package com.dio.datastore;

import com.dio.skeleton.Event;

import java.util.ArrayList;

public interface CalendarDataStore {

    void publish(Event event);

    Event remove(String eventName);

    Event getEvent(String name);

    ArrayList<Event> getAllEvent();
}