package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable
{
    public AnchorPane center_container;
    public AnchorPane menu_container;
    public AnchorPane top_bar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Model.get_model().get_view_manager().get_admin_option_clicked().addListener(((observableValue, old_value, new_value) -> {
            try {change_center(new_value);}
            catch (IOException e) {throw new RuntimeException(e);}
        }));

        try
        {
            center_container.getChildren().add(Model.get_model().get_view_manager().get_admin_profile());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void change_center(String new_value) throws IOException
    {
        center_container.getChildren().clear();

        switch (new_value)
        {
            case "Clientele" -> center_container.getChildren().add(Model.get_model().get_view_manager().get_clientele_view());
            case "Help Center" -> Model.get_model().get_view_manager().show_customer_care();
            case "About" -> Model.get_model().get_view_manager().show_about_us();
            case "Log in Activity" -> center_container.getChildren().add(Model.get_model().get_view_manager().get_log_in_activity());
            default -> center_container.getChildren().add(Model.get_model().get_view_manager().get_admin_profile());
        }
    }
}
