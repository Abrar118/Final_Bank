package com.example.final_bank.View_manager;

import com.example.final_bank.Controllers.Admin.Quick_link_controller;
import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Quick_link;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class Quick_link_cell_factory extends ListCell<Quick_link>
{
    @Override
    protected void updateItem(Quick_link quickLink, boolean empty)
    {
        super.updateItem(quickLink, empty);

        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Admin/quick-link-cell.fxml"));
            Quick_link_controller quickLinkController = new Quick_link_controller(quickLink);
            fxmlLoader.setController(quickLinkController);
            setText(null);

            try {setGraphic(fxmlLoader.load());}
            catch (IOException e) { e.printStackTrace();}
        }
    }
}
