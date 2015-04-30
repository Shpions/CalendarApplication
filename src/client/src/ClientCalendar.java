package src;

import com.dio.service.ServiceInterface;

public class ClientCalendar {
    private ServiceInterface serviceInterface;

    public void setServiceInterface(ServiceInterface serviceInterface){
        this.serviceInterface = serviceInterface;
    }

    public ServiceInterface getServiceInterface(){
        return serviceInterface;
    }
}
