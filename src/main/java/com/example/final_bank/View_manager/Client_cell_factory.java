package com.example.final_bank.View_manager;

import com.example.final_bank.Controllers.Admin.Client_cell_controller;
import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class Client_cell_factory extends ListCell<Client>
{
    @Override
    protected void updateItem(Client client, boolean empty)
    {
        super.updateItem(client, empty);

        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Admin/client-cell.fxml"));
            Client_cell_controller client_cell_controller = new Client_cell_controller(client);
            fxmlLoader.setController(client_cell_controller);
            setText(null);

            try { setGraphic(fxmlLoader.load());}
            catch (IOException e) { e.printStackTrace();}
        }
    }
}
