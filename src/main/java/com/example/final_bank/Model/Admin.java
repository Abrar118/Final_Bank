package com.example.final_bank.Model;

public class Admin extends Person
{
    String pin;
    public Admin(String mail, String password, String name, String gender, String birthdate, String facebook, String home_address, String pin)
    {
        super(mail, password, name, gender, birthdate, facebook, home_address);
        this.pin = pin;
    }

    public Admin()
    {
        super("","","","","","","");
        this.pin = "";
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }
}
