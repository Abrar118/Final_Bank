package com.example.final_bank.View_manager;

import com.example.final_bank.Controllers.Client.Deposit_view_cell_controller;
import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Deposit;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class Small_deposit_cell_factory extends ListCell<Deposit>
{
    @Override
    protected void updateItem(Deposit deposit, boolean b)
    {
        super.updateItem(deposit, b);

        if(b)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Admin/small-deposit-cell.fxml"));
            Deposit_view_cell_controller deposit_view_cell_controller = new Deposit_view_cell_controller(deposit);
            fxmlLoader.setController(deposit_view_cell_controller);
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
