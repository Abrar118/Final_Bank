package com.example.final_bank.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Deposit
{
    final DoubleProperty amount;
    final StringProperty reference;
    final StringProperty time;

    public Deposit(double amount, String reference, String time)
    {
        this.amount = new SimpleDoubleProperty(this, "amount", amount);
        this.reference = new SimpleStringProperty(this, "reference", reference);
        this.time = new SimpleStringProperty(this,"time", time);

    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public String getReference() {
        return reference.get();
    }

    public StringProperty referenceProperty() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference.set(reference);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}
