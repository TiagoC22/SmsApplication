package com.rodpil.smsstat;

/**
 * Created by Tiago on 25/05/2018.
 */

public class ContactOff {

    private String name, phone, counter;

    public ContactOff(String name, String phone, String counter) {
        this.name = name;
        this.counter = counter;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCounter() {
        return counter;
    }
}
