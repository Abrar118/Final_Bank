package com.example.final_bank.Controllers.Client;

import com.example.final_bank.Model.Client;
import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Fpath;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class profile implements Initializable
{
    public Label profile_name;
    public Circle profile_pic;
    public FontAwesomeIconView facebook_icon;
    public FontAwesomeIconView email_icon;
    public Button about;
    public Button accounts;
    public Button edit_profile;
    public Label email;
    public Label show_date_of_birth;
    public Label gender;
    public Label address;
    public Hyperlink facebook;
    public Label checking_balance;
    public Label savings_balance;
    public TextField first_name;
    public TextField last_name;
    public TextField set_home;
    public TextField set_facebook;
    public PasswordField password;
    public PasswordField confirm_pass;
    public Button save_changes;
    public Label message;
    public AnchorPane edit_pane;
    public AnchorPane accounts_pane;
    public AnchorPane about_pane;
    public Rectangle rect1, rect2, rect3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        set_view();

        add_listeners();

        set_animation(rect1, 10000, 1);
        set_animation(rect2, 8000, -1);
        set_animation(rect3, 5000, 1);
    }

    private void add_listeners()
    {
        facebook.setOnMouseClicked(MouseEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www."+facebook.getText()));}
            catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        });

        facebook_icon.setOnMouseClicked(MouseEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www."+facebook.getText()));}
            catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        });

        email_icon.setOnMouseClicked(MouseEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.gmail.com"));}
            catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        });

        about.setOnMouseClicked(MouseEvent -> {about_pane.setVisible(true); accounts_pane.setVisible(false); edit_pane.setVisible(false);});
        accounts.setOnMouseClicked(MouseEvent -> {about_pane.setVisible(false); accounts_pane.setVisible(true); edit_pane.setVisible(false);});
        edit_profile.setOnMouseClicked(MouseEvent -> {about_pane.setVisible(false); accounts_pane.setVisible(false); edit_pane.setVisible(true);});

        save_changes.setOnMouseClicked(MouseEvent -> update_info());
    }

    private void set_view()
    {
        profile_pic.setFill(new ImagePattern(Model.get_model().current_client.get_profile_pic()));
        profile_name.setText(Model.get_model().current_client.getName());
        email.setText(Model.get_model().current_client.getMail());
        facebook.setText(Model.get_model().current_client.getFacebook());
        show_date_of_birth.setText(Model.get_model().current_client.getBirthdate());
        address.setText(Model.get_model().current_client.getHome_address());

        checking_balance.setText(String.valueOf(Model.get_model().checking_account.getBalance()));
        savings_balance.setText(String.valueOf(Model.get_model().savings_account.getBalance()));
    }

    private void update_info()
    {
        Client client = new Client();

        if(!first_name.getText().isBlank() && !last_name.getText().isBlank()) client.setName(first_name.getText()+" "+last_name.getText());
        else client.setName(Model.get_model().current_client.getName());

        profile_name.setText(client.getName());

        if(!password.getText().isBlank() && !confirm_pass.getText().isBlank())
        {
            if(password.getText().equals(confirm_pass.getText())) client.setPassword(password.getText());
            else
            {
                password.setText("Password don't match");
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event-> password.clear()));
                timeline.play();
                client.setPassword(Model.get_model().current_client.getPassword());
            }
        }
        else client.setPassword(Model.get_model().current_client.getPassword());

        if(!set_facebook.getText().isBlank()) client.setFacebook(set_facebook.getText());
        else client.setFacebook(Model.get_model().current_client.getFacebook());

        facebook.setText(client.getFacebook());

        if(!set_home.getText().isBlank()) client.setHome_address(set_home.getText());
        else client.setHome_address(Model.get_model().current_client.getHome_address());

        client.setGender(Model.get_model().current_client.getGender());
        client.setBirthdate(Model.get_model().current_client.getBirthdate());
        client.setMail(Model.get_model().current_client.getMail());
        client.set_profile_pic(Model.get_model().current_client.get_profile_pic());

        Model.get_model().current_client.setName(client.getName());
        Model.get_model().current_client = client;

        try
        {
            File file = new File(Fpath.data_path+"Data/Client/"+Model.get_model().current_client.getMail()+"/name-pass.txt");
            FileWriter cout = new FileWriter(file);

            cout.write(client.getMail()+"\n");
            cout.write(client.getPassword()+"\n");
            cout.write(client.getName()+"\n");
            cout.write(client.getGender()+"\n");
            cout.write(client.getBirthdate()+"\n");
            cout.write(client.getFacebook()+"\n");
            cout.write(client.getHome_address()+"\n");
            cout.close();

            message.setText("Profile updated");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->
            {
                message.setText("");
                first_name.clear();
                last_name.clear();
                password.clear();
                confirm_pass.clear();
                set_home.clear();
                set_facebook.clear();
            }));
            timeline.play();

        }
        catch (IOException  e)
        {
            e.printStackTrace();

            message.setText("Not saved");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event-> message.setText("")));
            timeline.play();
        }
    }

    private void set_animation(Rectangle rect, double time, int direction)
    {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), rect);
        rotateTransition.setByAngle(direction*360);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    }
}
