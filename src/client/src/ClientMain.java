package src;

import com.dio.datastore.CalendarAdapter;
import com.dio.parser.Parser;
import com.dio.parser.ParserImpl;
import com.dio.service.ServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.logging.Logger;

public class ClientMain {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-client.xml");
        ServiceInterface service = (ServiceInterface) context.getBean("service");
        CalendarAdapter calendarAdapter = new CalendarAdapter();
        Parser p = new ParserImpl(calendarAdapter);



    }
}
