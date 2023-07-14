package com.example.final_bank.Controllers;

import com.example.final_bank.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class policy implements Initializable
{

    public Button accept;
    public Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        accept.setOnMouseClicked(MouseEvent -> Model.get_model().get_view_manager().close_stage(accept));
        cancel.setOnMouseClicked(MouseEvent -> Model.get_model().get_view_manager().close_stage(accept));
    }
}
