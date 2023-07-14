package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Quick_link;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Quick_link_controller implements Initializable
{

    public ImageView logo;
    public Label link_name;
    public Label description;
    public Button go_to_link_button;
    private final Quick_link quickLink;

    public Quick_link_controller(Quick_link quickLink)
    {
        this.quickLink = quickLink;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        logo.setImage(quickLink.getLogo());
        link_name.setText(quickLink.getLink_name());
        description.setText(quickLink.getDescription());

        go_to_link_button.setOnMouseClicked(MouseEvent -> {
            try
            {
                Desktop.getDesktop().browse(new URI(quickLink.getLink()));
            }
            catch (IOException | URISyntaxException e)
            {
                e.printStackTrace();
            }
        });
    }
}
