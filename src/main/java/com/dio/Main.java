package com.dio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        CallendarService calendarService = (CallendarService) context.getBean("service");
        Date d = new Date(2015,3,13);
        Event meeting = new Event.Builder()
                .setTitle("Birthday")
                .setDescription("Just abirthday party")
                .setAttenders(Arrays.asList("x@ukr.net", "ivon@yahoo.com", "ton@zen.ru"))
                .setDate(d)
                .build();


        System.out.println(meeting);
    }
}