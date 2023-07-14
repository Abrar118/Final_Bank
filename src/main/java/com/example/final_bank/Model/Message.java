package com.example.final_bank.Model;

public class Message
{
    String message_body;

    public Message(String message_body)
    {
        this.message_body = message_body;
    }

    public Message()
    {
        this.message_body = "";
    }


    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }
}
