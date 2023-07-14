package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Admin;
import com.example.final_bank.Model.Model;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menu_controller implements Initializable
{
    public AnchorPane manu_names_pane;
    public Button profile;
    public Button clientele;
    public Button log_out;
    public Button help_center;
    public Button about;
    public AnchorPane menu_icon_pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        log_out.setOnMouseClicked(MouseEvent -> {try {back_to_log_in();}
        catch (IOException e) {throw new RuntimeException(e);}});

        manu_names_pane.setOnMouseExited(MouseEvent->name_plane_disappear());
    }

    public void set_option_clicked(MouseEvent mouseEvent)
    {
        Button option = (Button) mouseEvent.getSource();
        Model.get_model().get_view_manager().get_admin_option_clicked().set(option.getText());
    }

    public void name_plane_appear()
    {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), manu_names_pane);
        transition.setToX(147);
        transition.play();
    }

    private void name_plane_disappear()
    {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), manu_names_pane);
        transition.setToX(0);
        transition.play();
    }

    private void back_to_log_in() throws IOException
    {
        Model.get_model().get_view_manager().close_stage(log_out);

        Model.get_model().get_view_manager().show_log_in_screen();
        Model.get_model().get_view_manager().get_admin_option_clicked().set("");
        Model.get_model().current_admin = new Admin();
    }
}
