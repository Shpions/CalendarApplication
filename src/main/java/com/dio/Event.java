package com.dio;



import java.util.Date;
import java.util.List;



public class Event {
    private String description;
    private List<String> attenders;
    private Date date;
    private String title;


    private Event(Builder builder) {
        this.description = builder.description;
        this.attenders = builder.attenders;
        this.date = builder.date;
        this.title = builder.title;
    }

    @Override
    public String toString() {
        return "Event{" +
                "description='" + description + '\'' +
                ", attenders=" + attenders +
                ", date=" + date +
                ", title='" + title + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAttenders() {
        return attenders;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public static class Builder{
        private String description;
        private List<String> attenders;
        private Date date;
        private String title;

        public Builder() {
        }

        public Builder(Event e) {
            this.description = e.description;
            this.attenders = e.attenders;
            this.date = e.date;
            this.title = e.title;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setAttenders(List<String> attenders) {
            this.attenders = attenders;
            return this;
        }

        public Builder setDate() {
            this.date = date;
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
