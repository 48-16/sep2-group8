package model;

import java.sql.Date;
import java.sql.Time;

public class appointment {

    private Date date;
    private Time time;
    private user barber;
    private user client;
    private price price;

    public appointment(Date date,
                       Time time,
                       user barber,
                       user client,
                       price price) {
        this.date = date;
        this.time = time;
        this.barber = barber;
        this.client = client;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public user getBarber() {
        return barber;
    }

    public void setBarber(user barber) {
        this.barber = barber;
    }

    public user getClient() {
        return client;
    }

    public void setClient(user client) {
        this.client = client;
    }

    public double getPrice(double discount) {
        return price.getPrice(discount);
    }

    public void setPrice(double price) {
        this.price.setPrice(price);
    }
}