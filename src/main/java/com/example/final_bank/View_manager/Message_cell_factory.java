package com.example.final_bank.View_manager;

import com.example.final_bank.Controllers.Admin.Message_cell_controller;
import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Message;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class Message_cell_factory extends ListCell<Message>
{
    @Override
    protected void updateItem(Message message, boolean empty)
    {
        super.updateItem(message, empty);

        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Admin/message_cell.fxml"));
            Message_cell_controller messageCellController = new Message_cell_controller(message);
            fxmlLoader.setController(messageCellController);
            setText(null);

            try {setGraphic(fxmlLoader.load());}
            catch (IOException e) { e.printStackTrace();}
        }
    }
}
