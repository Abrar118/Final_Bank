package com.example.final_bank.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

abstract class Person
{
    String mail;
    String password;
    StringProperty name;
    String gender;
    String birthdate;
    String facebook;
    String home_address;
    Image profile_pic;

    public Person(String mail, String password, String name, String gender, String birthdate, String facebook, String home_address)
    {
        this.mail = mail;
        this.password = password;
        this.name = new SimpleStringProperty(name);
        this.gender = gender;
        this.birthdate = birthdate;
        this.facebook = facebook;
        this.home_address = home_address;
        this.profile_pic = null;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty get_name_property()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public Image get_profile_pic()
    {
        return profile_pic;
    }

    public void set_profile_pic(Image image)
    {
        profile_pic = image;
    }
}

