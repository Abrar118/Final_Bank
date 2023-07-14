package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Client;
import com.example.final_bank.View_manager.Fpath;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Client_cell_controller implements Initializable
{
    public Circle image;
    public Label name;
    public Label email;
    public FontAwesomeIconView gender_icon;
    private final Client client;

    public Client_cell_controller(Client client)
    {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        image.setFill(new ImagePattern(new Image(Fpath.data_path+"Data/Client/"+client.getMail()+"/avatar.png")));
        name.setText(client.getName());
        email.setText(client.getMail());

        if(client.getGender().equals("Male")) gender_icon.setGlyphName("MALE");
        else gender_icon.setGlyphName("FEMALE");
    }
}
