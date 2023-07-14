package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Admin;
import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Fpath;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    public Rectangle rect2;
    public Rectangle rect3;
    public Rectangle rect1;
    public Circle profile_pic;
    public FontAwesomeIconView facebook_icon;
    public FontAwesomeIconView email_icon;
    public Button about;
    public Button edit_profile;
    public AnchorPane about_pane;
    public Label email;
    public Hyperlink facebook;
    public Label show_date_of_birth;
    public Label gender;
    public Label address;
    public AnchorPane edit_pane;
    public TextField first_name;
    public TextField last_name;
    public TextField set_home;
    public TextField set_facebook;
    public PasswordField password;
    public PasswordField confirm_pass;
    public Button save_changes;
    public Label message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        set_view();
        set_listeners();

        set_animation(rect1, 10000, 1);
        set_animation(rect2, 8000, -1);
        set_animation(rect3, 5000, 1);
    }

    private void set_view()
    {
        profile_pic.setFill(new ImagePattern(Model.get_model().current_admin.get_profile_pic()));
        profile_name.setText(Model.get_model().current_admin.getName());
        email.setText(Model.get_model().current_admin.getMail());
        facebook.setText(Model.get_model().current_admin.getFacebook());
        show_date_of_birth.setText(Model.get_model().current_admin.getBirthdate());
        address.setText(Model.get_model().current_admin.getHome_address());
    }

    private void set_listeners()
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

        about.setOnMouseClicked(MouseEvent -> {about_pane.setVisible(true); edit_pane.setVisible(false);});
        edit_profile.setOnMouseClicked(MouseEvent -> {about_pane.setVisible(false); edit_pane.setVisible(true);});

        save_changes.setOnMouseClicked(MouseEvent -> update_info());
    }

    private void set_animation(Rectangle rect, double time, int direction)
    {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), rect);
        rotateTransition.setByAngle(direction*360);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    }

    private void update_info()
    {
        Admin admin = new Admin();

        if(!first_name.getText().isBlank() && !last_name.getText().isBlank()) admin.setName(first_name.getText()+" "+last_name.getText());
        else admin.setName(Model.get_model().current_admin.getName());

        profile_name.setText(admin.getName());

        if(!password.getText().isBlank() && !confirm_pass.getText().isBlank())
        {
            if(password.getText().equals(confirm_pass.getText())) admin.setPassword(password.getText());
            else
            {
                password.setText("Password don't match");
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event-> password.clear()));
                timeline.play();
                admin.setPassword(Model.get_model().current_admin.getPassword());
            }
        }
        else admin.setPassword(Model.get_model().current_admin.getPassword());

        if(!set_facebook.getText().isBlank()) admin.setFacebook(set_facebook.getText());
        else admin.setFacebook(Model.get_model().current_admin.getFacebook());

        facebook.setText(admin.getFacebook());

        if(!set_home.getText().isBlank()) admin.setHome_address(set_home.getText());
        else admin.setHome_address(Model.get_model().current_admin.getHome_address());

        admin.setGender(Model.get_model().current_admin.getGender());
        admin.setBirthdate(Model.get_model().current_admin.getBirthdate());
        admin.setMail(Model.get_model().current_admin.getMail());
        admin.setPin(Model.get_model().current_admin.getPin());
        admin.set_profile_pic(Model.get_model().current_admin.get_profile_pic());

        Model.get_model().current_admin.setName(admin.getName());
        Model.get_model().current_admin = admin;

        try
        {
            File file = new File(Fpath.data_path+"Data/Admin/"+Model.get_model().current_admin.getMail()+"/name-pass.txt");
            FileWriter cout = new FileWriter(file);

            cout.write(admin.getMail()+"\n");
            cout.write(admin.getPassword()+"\n");
            cout.write(admin.getPin()+"\n");
            cout.write(admin.getName()+"\n");
            cout.write(admin.getGender()+"\n");
            cout.write(admin.getBirthdate()+"\n");
            cout.write(admin.getFacebook()+"\n");
            cout.write(admin.getHome_address()+"\n");
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

}
