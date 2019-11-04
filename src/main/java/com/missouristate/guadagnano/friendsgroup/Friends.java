package com.missouristate.guadagnano.friendsgroup;

public class Friends {
    private int id;
    private String name;
    private String email;

    public Friends (int newId, String newName, String newEmail){
        setID(newId);
        setName(newName);
        setEmail(newEmail);
    }

    private void setName(String newName) {

        name = newName;
    }

    private void setID(int newId) {

        id = newId;
    }

    private void setEmail(String newEmail) {
        email = newEmail;
    }

    public int getId() {

        return id;
    }


    public String getName() {

        return name;
    }

    public String getEmail() {

        return email;
    }

    public String toString() {

        return id + "; " + name + ";"+ email;
    }
}
