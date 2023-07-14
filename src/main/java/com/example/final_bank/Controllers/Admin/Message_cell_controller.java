package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Message;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Message_cell_controller implements Initializable
{
    public Label body;
    private final Message message;
    public Text rating;

    public Message_cell_controller(Message message)
    {
        this.message = message;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        body.setText(message.getMessage_body());
        rating.setText("M");
    }
}
