package com.example.wazifa.calendar;

/**
 * Created by Manny on 3/25/16.
 */
public class Profile {
    private String name;
    //TODO profile should also store and image of user

    public Profile() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
