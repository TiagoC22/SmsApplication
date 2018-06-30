package com.rodpil.smsstat;

/**
 * Created by Tiago on 25/05/2018.
 */

public class Objects {
    private String name;
    private String phone;
    private String counter;

    public Objects(String name, String phone, String counter) {
        this.name = name;
        this.phone = phone;
        this.counter = counter;
    }

    public String getCounter() {
        return counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}