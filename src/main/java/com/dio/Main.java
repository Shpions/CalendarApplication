package com.dio;

import java.util.ArrayList;
import java.util.List;

public class Main {
    List<Event> list;

    public void main(List<String> discriptions) {

        list = new ArrayList<Event>();

        for (String title : discriptions) {
            Event event = new Event.Builder().setTitle(title).build();
            list.add(event);
        }
    }

    public static void main(String[] args) {

    }
}