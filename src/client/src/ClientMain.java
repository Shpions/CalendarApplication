package src;

import com.dio.datastore.CalendarAdapter;
import com.dio.parser.Parser;
import com.dio.parser.ParserImpl;
import com.dio.service.ServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;

public class ClientMain {

    public static void main(String[] args) throws RemoteException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-client.xml");
        ServiceInterface service = (ServiceInterface) context.getBean("service");
        CalendarAdapter calendarAdapter = new CalendarAdapter();
        Parser p = new ParserImpl(calendarAdapter);



    }
}
