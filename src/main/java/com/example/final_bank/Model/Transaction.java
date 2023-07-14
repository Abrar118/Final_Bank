package com.example.final_bank.Model;

import javafx.beans.property.*;

public class Transaction
{
    final StringProperty receiver;
    final DoubleProperty amount;
    final StringProperty message;
    final StringProperty time;
    final BooleanProperty type; //false means receive and true means send


    public Transaction(String receiver, double amount, String message, String time, boolean type)
    {
        this.receiver = new SimpleStringProperty(this, "receiver", receiver);
        this.amount = new SimpleDoubleProperty(this, "amount", amount);
        this.message = new SimpleStringProperty(this, "message", message);
        this.time = new SimpleStringProperty(this, "time", time);
        this.type = new SimpleBooleanProperty(this, "type", type);
    }

    public String getReceiver() {
        return receiver.get();
    }

    public StringProperty receiverProperty() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver.set(receiver);
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

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
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

    public boolean isType() {
        return type.get();
    }

    public BooleanProperty typeProperty() {
        return type;
    }

    public void setType(boolean type) {
        this.type.set(type);
    }
}
