package com.dio;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class SpringAppTests{


    @Test
    public void test_search() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        CallendarService calendarService = (CallendarService) context.getBean("service");
        Date d = new Date(2015,3,13);
        Event meeting = new Event.Builder()
                .setTitle("Birthday")
                .setDescription("Just abirthday party")
                .setAttenders(Arrays.asList("x@ukr.net", "ivon@yahoo.com", "ton@zen.ru"))
                .setDate(d)
                .build();
        String s [] = {"x@ukr.net", "ivon@yahoo.com", "ton@zen.ru"};
        List<String> l = new LinkedList<String>(Arrays.asList(s));

        calendarService.addEvent(calendarService.createEvent("Beer", "Drunk party", new Date(2015, 6, 21), l));
        calendarService.addEvent(meeting);
        Assert.assertEquals(calendarService.search(meeting.getTitle()), meeting);
    }


    @Test
    public void test_create() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        CallendarService calendarService = (CallendarService) context.getBean("service");
        Date d = new Date(2015,3,13);
        Event meeting = new Event.Builder()
                .setTitle("Birthday")
                .setDescription("Just abirthday party")
                .setAttenders(Arrays.asList("x@ukr.net", "ivon@yahoo.com", "ton@zen.ru"))
                .setDate(d)
                .build();
        String s [] = {"x@ukr.net", "ivon@yahoo.com", "ton@zen.ru"};
        List<String> l = new LinkedList<String>(Arrays.asList(s));

        Event e = calendarService.createEvent("Beer", "Drunk party", new Date(2015, 6, 21), l);
        Map <String, Event> m = new HashMap<String, Event>();
        
        calendarService.addEvent(e);
        Assert.assertEquals(calendarService.search(e.getTitle()), e);
    }

}
