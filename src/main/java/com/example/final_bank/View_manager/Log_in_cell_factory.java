package com.example.final_bank.View_manager;

import com.example.final_bank.Controllers.Admin.Log_in_cell_controller;
import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Log_in;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class Log_in_cell_factory extends ListCell<Log_in>
{
    @Override
    protected void updateItem(Log_in logIn, boolean empty) {
        super.updateItem(logIn, empty);

        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Admin/log-in-cell.fxml"));
            Log_in_cell_controller logInCellController = new Log_in_cell_controller(logIn);
            fxmlLoader.setController(logInCellController);
            setText(null);

            try {
                setGraphic(fxmlLoader.load());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
