package com.example.final_bank.Controllers;

import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class about_us implements Initializable
{
    public FontAwesomeIconView general_facebook;
    public FontAwesomeIconView general_twitter;
    public FontAwesomeIconView general_instagram;
    public Button contact_us;
    public Button get_the_app;
    public HBox abrar;
    public Rectangle abrar_rect;
    public HBox mehmil;
    public Rectangle mehmil_rect;
    public HBox trisha;
    public Rectangle trisha_rect;
    public AnchorPane welcome_text;
    public Button download_now;
    public FontAwesomeIconView abrar_dot;
    public FontAwesomeIconView mehmil_dot;
    public FontAwesomeIconView trisha_dot;
    public Text privacy_policy;
    public Text close;
    public AnchorPane abrar_pane;
    public Hyperlink abrar_facebook;
    public Hyperlink abrar_insta;
    public Hyperlink abrar_github;
    public AnchorPane mehmil_pane;
    public Hyperlink mehmil_facebook;
    public Hyperlink mehmil_insta;
    public AnchorPane trisha_pane;
    public Hyperlink trisha_facebook;
    public Hyperlink trisha_insta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        close.setOnMouseClicked(e -> Model.get_model().get_view_manager().close_stage(close));
        privacy_policy.setOnMouseClicked(MouseEvent -> {try {Model.get_model().get_view_manager().show_policy();}
        catch (IOException e) {e.printStackTrace();}});

        initial_set_view();
        set_listeners();
    }

    private void initial_set_view()
    {
        try {
            abrar_rect.setFill(new ImagePattern(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/abrar_50.jpg")).openStream())));
            mehmil_rect.setFill(new ImagePattern(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/left_mehmil_50.jpg")).openStream())));
            trisha_rect.setFill(new ImagePattern(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/left_trisha_50.jpg")).openStream())));

            abrar_dot.setFill(new Color(1.0, 1.0, 1.0, 1.0));
            mehmil_dot.setFill(new Color(1.0, 1.0, 1.0, 1.0));
            trisha_dot.setFill(new Color(1.0, 1.0, 1.0, 1.0));

            welcome_text.setVisible(true);
            abrar_pane.setVisible(false);
            mehmil_pane.setVisible(false);
            trisha_pane.setVisible(false);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void set_listeners()
    {
        download_now.setOnMouseClicked(e -> {
        try {Desktop.getDesktop().browse(new URI("https://drive.google.com/drive/folders/1r2Jh3eda8bCOFR6Xo4kKjwPoDIH8L6dY?usp=share_link"));}
        catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});

        get_the_app.setOnMouseClicked(e -> {
        try {Desktop.getDesktop().browse(new URI("https://drive.google.com/drive/folders/1r2Jh3eda8bCOFR6Xo4kKjwPoDIH8L6dY?usp=share_link"));}
        catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});

        contact_us.setOnMouseClicked(e -> {
        try {Model.get_model().get_view_manager().show_customer_care();}
        catch (IOException ex) {throw new RuntimeException(ex);}});

        general_facebook.setOnMouseClicked(e -> {
        try {Desktop.getDesktop().browse(new URI("https://www.facebook.com"));}
        catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});

        general_instagram.setOnMouseClicked(e -> {
        try {Desktop.getDesktop().browse(new URI("https://www.instagram.com"));}
        catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});

        general_twitter.setOnMouseClicked(e -> {
        try {Desktop.getDesktop().browse(new URI("https://www.twitter.com"));}
        catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});

        set_view_listeners();
    }

    private void set_view_listeners()
    {
        abrar.setOnMouseClicked(e ->show_abrar());
        mehmil.setOnMouseClicked(e -> show_mehmil());
        trisha.setOnMouseClicked(e -> show_trisha());

        abrar_facebook.setOnMouseClicked(e -> {
            try {Desktop.getDesktop().browse(new URI("https://www."+abrar_facebook.getText()));}
            catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});
        abrar_insta.setOnMouseClicked(e -> {
            try {Desktop.getDesktop().browse(new URI("https://www."+abrar_insta.getText()));}
            catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});
        abrar_github.setOnMouseClicked(e -> {
            try {Desktop.getDesktop().browse(new URI(abrar_github.getText()));}
            catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});

        mehmil_facebook.setOnMouseClicked(e -> {
            try {Desktop.getDesktop().browse(new URI("https://www."+mehmil_facebook.getText()));}
            catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});
        mehmil_insta.setOnMouseClicked(e -> {
            try {Desktop.getDesktop().browse(new URI("https://www."+mehmil_insta.getText()));}
            catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});

        trisha_facebook.setOnMouseClicked(e -> {
            try {Desktop.getDesktop().browse(new URI("https://www."+trisha_facebook.getText()));}
            catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});
        trisha_insta.setOnMouseClicked(e -> {
            try {Desktop.getDesktop().browse(new URI("https://www."+trisha_insta.getText()));}
            catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}});
    }

    private void show_abrar()
    {
        welcome_text.setVisible(false);
        abrar_pane.setVisible(true);
        mehmil_pane.setVisible(false);
        trisha_pane.setVisible(false);

        abrar_dot.setFill(new Color(0,0,0,1));
        mehmil_dot.setFill(new Color(1.0, 1.0, 1.0, 1.0));
        trisha_dot.setFill(new Color(1.0, 1.0, 1.0, 1.0));
    }

    private void show_mehmil()
    {
        welcome_text.setVisible(false);
        abrar_pane.setVisible(false);
        mehmil_pane.setVisible(true);
        trisha_pane.setVisible(false);

        abrar_dot.setFill(new Color(1,1,1,1));
        mehmil_dot.setFill(new Color(0, 0, 0, 1.0));
        trisha_dot.setFill(new Color(1.0, 1.0, 1.0, 1.0));
    }

    private void show_trisha()
    {
        welcome_text.setVisible(false);
        abrar_pane.setVisible(false);
        mehmil_pane.setVisible(false);
        trisha_pane.setVisible(true);

        abrar_dot.setFill(new Color(1,1,1,1));
        mehmil_dot.setFill(new Color(1.0, 1.0, 1.0, 1.0));
        trisha_dot.setFill(new Color(0, 0, 0, 1.0));
    }
}
