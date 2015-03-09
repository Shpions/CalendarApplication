package com.dio;

import com.dio.Service.CalendarService;
import com.dio.Skeleton.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class ClientMain {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        CalendarService service = (CalendarService) context.getBean("service");
        Person p1 = new Person.Builder().email("xxii@ukr.net").firstName("Stas").phone("0502622433").secondName("Grin").build();
        Person p2 = new Person.Builder().email("pens@yahoo.com").firstName("Alex").phone("0503949454").secondName("Penskiy").build();
        Person p3 = new Person.Builder().email("ksu@ukr.net").firstName("Ksu").phone("093").secondName("Havr").build();
        Person p4 = new Person.Builder().email("gva-hr@ukr.net").firstName("Vlad").phone("0674934223").secondName("Grin").build();
        Person p5 = new Person.Builder().email("polik@gmail.com").firstName("Aleksandr").phone("05024321356").secondName("Polik").build();
        ArrayList <Person> list1 = new ArrayList <Person> ();
        list1.add(p1); list1.add(p2); list1.add(p3); list1.add(p4); list1.add(p5);
        ArrayList <Person> list2 = new ArrayList<Person>();
        list2.add(p1);  list2.add(p4); list2.add(p5);


        Person p6 = new Person.Builder().email("ksu@kr.net").firstName("Ksdfsu").phone("093r55").secondName("Hav").build();
        Person p7 = new Person.Builder().email("ksu@net").firstName("Ksdsfu").phone("09345fg3").secondName("Har").build();
    list2.add(p6);

        service.add_Event(service.create_Event("Drunk Party", "just a drunk party", new Date(2015,3,5,19,45),
                new Date(2015,3,5,22,45), list1));
        service.add_Event(service.create_Event("Event", "buisness", new Date(2015,3,5,16,30), new Date(2015,3,5,17,45), list2));
        service.add_Event(service.create_Event("Eaaavent", "buisssssness", new Date(2015,3,5,23,00), new Date(2015,3,5,23,15), list1));

        service.add_Event(service.create_Event("Checkt", "test", new Date(2015,3,6,23,30), new Date(2015,3,6,23,40), list1));
        //System.out.println(service.get_Time_For_Event(new Date(2015,3,5,16,50), s));

        //System.out.println(service.find_Event_By_time("xxi@ukr.net", new Date(2015,3,5,16,55)));

            logger.info("Created event in data store: " + service.searchEventByDay(new Date(2015,3,5)));
           // logger.info("time: " + service.get_Time_For_Event(new Date(2015,3,5,17,00), list2));
            //logger.info("time: " + service.get_Time_For_Event(new Date(2015,3,5,10,00), list2));

       System.out.println(service.searchEventByDay(new Date(2015, 3, 6, 23, 35)));

        logger.info("Service started");



    }
}
