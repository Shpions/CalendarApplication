package com.dio;

import com.dio.service.ServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.logging.Logger;

public class ServerMain {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ServiceInterface service = (ServiceInterface) context.getBean("service");

        service.initEvent();
    }
}
