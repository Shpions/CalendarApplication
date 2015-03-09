//local code review (vtegza): package name should always be in lover case @ 09.03.15
package com.dio.Skeleton;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Event implements Serializable{

    //local code review (vtegza): should be final @ 09.03.15
    private String description;
    private List<Person> attenders;
    private Date startDate;
    private Date endDate;
    private String title;


    private Event(Builder builder) {
        this.description = builder.description;
        this.attenders = builder.attenders;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.title = builder.title;
    }

    @Override
    public String toString() {
        return "Event{" +
                "description='" + description + '\'' +
                ", attenders=" + attenders +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                '}';
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public List<Person> getAttenders() {
        return attenders;
    }



    public String getTitle() {
        return title;
    }

    public static class Builder{
        private String description;
        private List<Person> attenders;
        private Date startDate;
        private Date endDate;
        private String title;

        public Builder() {
        }

        public Builder(Event e) {
            this.description = e.description;
            this.attenders = e.attenders;
            this.startDate = e.startDate;
            this.endDate = e.endDate;
            this.title = e.title;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setAttenders(List<Person> attenders) {
            this.attenders = attenders;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Event build(){
            return new Event(this);
        }
    }


}
