package com.example.final_bank.Controllers;

import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Initial_background_task;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.*;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class welcome_window implements Initializable
{
    public FontAwesomeIconView facebook;
    public FontAwesomeIconView instagram;
    public FontAwesomeIconView twitter;
    public AnchorPane terms_of_usage;
    public AnchorPane slide_show_pane;
    public ImageView slide_show;
    public Text contact_us;
    public Text logo;
    public ProgressBar download_progress;
    public Text about_us;
    private int slide_index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        slide_index=0;
        appear();

        if(!Model.get_model().get_view_manager().get_super_admin_status())
        {
            try {make_super_admin();}
            catch (IOException e) {throw new RuntimeException(e);}

            Model.get_model().get_view_manager().set_super_admin_status(true);
        }

        set_listeners();
    }

    private void set_listeners()
    {
        facebook.setOnMouseClicked(MouseEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.facebook.com"));}
            catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        });

        instagram.setOnMouseClicked(MouseEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.instagram.com"));}
            catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        });

        twitter.setOnMouseClicked(MouseEvent -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.twitter.com"));}
            catch (IOException | URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        });

        logo.setOnMouseClicked(MouseEvent-> {try {on_logo_click();}
        catch (IOException e) {throw new RuntimeException(e);}});

        contact_us.setOnMouseClicked(MouseEvent -> {try{Model.get_model().get_view_manager().show_customer_care();}
        catch (IOException e) {e.printStackTrace();}});

        terms_of_usage.setOnMouseClicked(MouseEvent -> {try {Model.get_model().get_view_manager().show_policy();}
        catch (IOException e) {e.printStackTrace();}});

        about_us.setOnMouseClicked(MouseEvent -> {try {Model.get_model().get_view_manager().show_about_us();}
        catch (IOException e) {e.printStackTrace();}});
    }


    private void make_super_admin() throws IOException
    {
        Initial_background_task initial_background_task = new Initial_background_task();

        download_progress.progressProperty().bind(initial_background_task.progressProperty());

        Thread thread = new Thread(initial_background_task);
        thread.setDaemon(true);
        thread.start();
    }


    private void on_logo_click() throws IOException
    {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), logo);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);
        scaleTransition.play();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event->{
            Model.get_model().get_view_manager().close_stage(logo);
            try {Model.get_model().get_view_manager().show_log_in_screen();}
            catch (IOException e) {throw new RuntimeException(e);}}));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void appear()
    {
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), facebook);
        transition.setFromY(100);
        transition.setToY(0);
        transition.play();

        TranslateTransition transition1 = new TranslateTransition(Duration.millis(500), instagram);
        transition1.setFromY(100);
        transition1.setToY(0);
        transition1.play();

        TranslateTransition transition2 = new TranslateTransition(Duration.millis(500), twitter);
        transition2.setFromY(100);
        transition2.setToY(0);
        transition2.play();

        TranslateTransition transition3 = new TranslateTransition(Duration.millis(1500), terms_of_usage);
        transition3.setFromX(-200);
        transition3.setToX(0);
        transition3.play();

        TranslateTransition transition4 = new TranslateTransition(Duration.millis(1500), slide_show_pane);
        transition4.setFromX(100);
        transition4.setToX(0);
        transition4.play();

        slideshow();
    }

    private void slideshow()
    {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/1st_slide.jpg")).toString()));
        images.add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/2nd_slide.jpg")).toString()));
        images.add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/3rd_slide.jpg")).toString()));
        images.add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/4th_slide.jpg")).toString()));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000),event-> slide_show.setImage(images.get(slide_index=(slide_index+1)%4))));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
