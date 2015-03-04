package com.dio;

import com.dio.Service.CalendarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ClientMain {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        CalendarService service = (CalendarService) context.getBean("service");

        String s[] ={"xxi@ukr.net","spy@yahoo.com", "go@gmail.com"};
        List<String> list = new ArrayList<String>(Arrays.asList(s));
        service.add_Event(service.create_Event("Drunk Party", "just a drunk party", new Date(2015,3,5,19,45), new Date(2015,3,5,22,45), list));
        service.add_Event(service.create_Event("Event", "buisness", new Date(2015,3,5,16,30), new Date(2015,3,5,17,45), list));
        service.add_Event(service.create_Event("Eaaavent", "buisssssness", new Date(2015,3,5,23,00), new Date(2015,3,5,23,15), list));
        //System.out.println(service.get_Time_For_Event(new Date(2015,3,5,16,50), s));

        //System.out.println(service.find_Event_By_time("xxi@ukr.net", new Date(2015,3,5,16,55)));

            //logger.info("Created event in data store: " + service.search_Event("Drunk Party"));
        System.out.println(service.get_Time_For_Event(new Date(2015, 3, 5, 17, 00), s));
        //System.out.println(service.get_Time_For_Event(new Date(2015, 3, 5, 19, 50), s));
    }
}
