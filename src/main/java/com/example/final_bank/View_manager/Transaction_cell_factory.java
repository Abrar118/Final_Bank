package com.example.final_bank.View_manager;

import com.example.final_bank.Controllers.Client.Transaction_cell_controller;
import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class Transaction_cell_factory extends ListCell<Transaction>
{
    @Override
    protected void updateItem(Transaction transaction, boolean empty)
    {
        super.updateItem(transaction, empty);

        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Client/transaction-view-cell.fxml"));
            Transaction_cell_controller transaction_cell_controller = new Transaction_cell_controller(transaction);
            fxmlLoader.setController(transaction_cell_controller);
            setText(null);

            try { setGraphic(fxmlLoader.load());}
            catch (IOException e) { e.printStackTrace(); }
        }
    }
}
