package com.example.final_bank.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

abstract class Account
{
    private final DoubleProperty balance;
    public Account(double passed_balance)
    {
        balance = new SimpleDoubleProperty(passed_balance);
    }

    public double getBalance() {
        return balance.get();
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }
}
