package com.example.final_bank.Model;

public class Client extends Person
{
    public Client(String mail, String password, String name, String gender, String birthdate, String facebook, String home_address)
    {
        super(mail, password, name, gender, birthdate, facebook, home_address);
    }

    public Client()
    {
        super("","","","","","","");
    }
}
