package com.example.final_bank.Model;

import com.example.final_bank.View_manager.View_manager;

public class Model
{
    private static Model model;
    private final View_manager view_manager;
    public Admin current_admin;
    public Client current_client;
    public Client selected_client;
    public Checking_account checking_account;
    public Savings_account savings_account;

    private Model()
    {
        this.view_manager = new View_manager();
        this.current_admin = new Admin();
        this.current_client = new Client();
        this.checking_account = new Checking_account(0);
        this.savings_account = new Savings_account(0);
        this.selected_client = new Client();
    }

    public static synchronized Model get_model()
    {
        if(model==null) model=new Model();
        return model;
    }

    public View_manager get_view_manager()
    {
        return view_manager;
    }
}
