package com.dio.parser;

import com.dio.datastore.CalendarAdapter;
import com.dio.datastore.EventAdapter;
import com.dio.datastore.PersonAdapter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ParserImpl implements Parser, Runnable {
    private final CalendarAdapter dataStore;

    public ParserImpl(CalendarAdapter dataStore) {

        this.dataStore = dataStore;
    }

    @Override
    public EventAdapter createEvent(String title, String description, Date startDate, Date endDate,
                                    List<PersonAdapter> attenders) throws RemoteException {
        EventAdapter eventAdapter = new EventAdapter();
        eventAdapter.setTitle(title);
        eventAdapter.setDescription(description);
        eventAdapter.setStartDate(startDate);
        eventAdapter.setEndDate(endDate);
        eventAdapter.setAttenders(attenders);
        return eventAdapter;
    }

    @Override
    public void addEvent(EventAdapter eventAdapter) throws RemoteException {
        try{
            File file = new File("C:\\forXmlPackage\\"+eventAdapter.getTitle());
            JAXBContext jaxbContext = JAXBContext.newInstance(EventAdapter.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(eventAdapter, file);
            jaxbMarshaller.marshal(eventAdapter, System.out);

        }catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    public ArrayList<EventAdapter> showAllEvents(){
        File files = new File("C:\\forXmlPackage\\");
        File [] fileList = files.listFiles();

        ArrayList<EventAdapter> adapterArrayList = new ArrayList<EventAdapter>();
        try {
            for(File file : fileList) {
                JAXBContext jaxbContext = JAXBContext.newInstance(EventAdapter.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                adapterArrayList.add((EventAdapter) unmarshaller.unmarshal(file));
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return adapterArrayList;
    }


    @Override
    public EventAdapter removeEvent(String title) throws RemoteException {
        File file = new File("C:\\forXmlPackage\\"+title);
        if(file.exists()){
            file.delete();
        }
        return null;
    }


    @Override
    public EventAdapter editEvent(String title, String description, Date startDate, Date endDate,
                                  List<PersonAdapter> attenders) throws RemoteException {
        removeEvent(title);
        EventAdapter eventAdapter = createEvent(title, description, startDate, endDate, attenders);
        addEvent(eventAdapter);
        return eventAdapter;
    }


}
