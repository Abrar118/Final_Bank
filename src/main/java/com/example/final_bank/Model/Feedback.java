package com.example.final_bank.Model;

public class Feedback {
    String body;
    int rating;

    public Feedback(String body, int rating)
    {
        this.body = body;
        this.rating = rating;
    }

    public Feedback()
    {
        this.body = "";
        this.rating = 1;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
