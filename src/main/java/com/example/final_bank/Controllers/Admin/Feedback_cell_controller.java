package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Feedback;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Feedback_cell_controller implements Initializable
{
    public Label body;
    private final Feedback feedback;
    public Text rating;

    public Feedback_cell_controller(Feedback feedback)
    {
        this.feedback=feedback;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        body.setText(feedback.getBody());
        rating.setText(String.valueOf(feedback.getRating()));
    }
}
