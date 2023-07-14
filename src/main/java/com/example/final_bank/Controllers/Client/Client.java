package com.example.final_bank.Controllers.Client;

import com.example.final_bank.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Client implements Initializable
{
    public AnchorPane top_bar;
    public AnchorPane center_container;
    public AnchorPane menu_container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Model.get_model().get_view_manager().get_customer_option_clicked().addListener(((observableValue, old_value, new_value) -> {
            try {change_center(new_value);}
            catch (IOException e) {throw new RuntimeException(e);}
        }));
    }

    private void change_center(String new_value) throws IOException
    {
        center_container.getChildren().clear();

        switch (new_value)
        {
            case "Transaction" -> center_container.getChildren().add(Model.get_model().get_view_manager().get_transaction_view());
            case "Deposit" -> center_container.getChildren().add(Model.get_model().get_view_manager().get_deposit_view());
            case "Customer care" -> Model.get_model().get_view_manager().show_customer_care();
            case "Profile" -> center_container.getChildren().add(Model.get_model().get_view_manager().get_customer_profile());
            case "About" -> Model.get_model().get_view_manager().show_about_us();
            default -> center_container.getChildren().add(Model.get_model().get_view_manager().get_client_dashboard());
        }
    }
}
