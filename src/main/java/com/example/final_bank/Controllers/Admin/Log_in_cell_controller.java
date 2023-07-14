package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Log_in;
import com.example.final_bank.View_manager.Fpath;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Log_in_cell_controller implements Initializable
{
    public Circle avatar;
    public Label username;
    public Label time;
    private final Log_in logIn;

    public Log_in_cell_controller(Log_in logIn)
    {
        this.logIn = logIn;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        avatar.setFill(new ImagePattern(new Image(Fpath.data_path+"Data/Client/"+logIn.getUsername()+"/avatar.png")));
        username.setText(logIn.getUsername());
        time.setText(logIn.getTime());
    }
}
