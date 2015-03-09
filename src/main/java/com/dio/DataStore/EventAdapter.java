package com.dio.DataStore;

import com.dio.Skeleton.Event;
import com.dio.Skeleton.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlType(name = "event")
public class EventAdapter implements Serializable {

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    private List<PersonAdapter> attenders;

    public EventAdapter() {
    }

    public EventAdapter(Event event) {
        this.name = event.getTitle();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();

        this.attenders = new ArrayList<PersonAdapter>();
        if (event.getAttenders() != null ) {
            for (Person person : event.getAttenders()) {
                this.attenders.add(new PersonAdapter(person));
            }

        }
    }

    @XmlElement(name = "person")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartTime(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndTime() {
        return endDate;
    }

    public void setEndTime(Date endTime) {
        this.endDate = endTime;
    }

    public List<PersonAdapter> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<PersonAdapter> attenders) {
        this.attenders = attenders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventAdapter that = (EventAdapter) o;

        if (attenders != null ? !attenders.equals(that.attenders) : that.attenders != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate!= null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (attenders != null ? attenders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventAdapter{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", startTime=").append(startDate);
        sb.append(", endTime=").append(endDate);
        sb.append(", attenders=").append(attenders);
        sb.append('}');
        return sb.toString();
    }
}