package com.dio.datastore;

import com.dio.skeleton.Event;
import com.dio.skeleton.Person;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class EventAdapter implements Serializable {

    private String title;
    private String description;
    private Date startDate;
    private Date endDate;

    private List<PersonAdapter> attenders;

    public EventAdapter() {
    }

    public EventAdapter(Event event) {
        this.title = event.getTitle();
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


    public String getTitle() {
        return title;
    }

    @XmlElement(name="title")
    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name="description")
    public void setDescription(String description) {
        this.description = description;
    }


    public Date getStartDate() {
        return startDate;
    }

    @XmlElement(name="startDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }

    @XmlElement(name="endDate")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<PersonAdapter> getAttenders() {
        return attenders;
    }

    @XmlElement(name="attenders")
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
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (attenders != null ? attenders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventAdapter{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", attenders=").append(attenders);
        sb.append('}');
        return sb.toString();
    }
}