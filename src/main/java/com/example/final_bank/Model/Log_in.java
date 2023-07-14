package com.example.final_bank.Model;

public class Log_in
{
    String username;
    String time;

    public Log_in(String username, String time)
    {
        this.username = username;
        this.time = time;
    }

    public Log_in()
    {
        this.username = "";
        this.time = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
