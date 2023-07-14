package com.example.final_bank.View_manager;

import com.example.final_bank.Controllers.Admin.Feedback_cell_controller;
import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Feedback;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class Feedback_cell_factory extends ListCell<Feedback>
{
    @Override
    protected void updateItem(Feedback feedback, boolean empty)
    {
        super.updateItem(feedback, empty);

        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Admin/message_cell.fxml"));
            Feedback_cell_controller feedbackCellController = new Feedback_cell_controller(feedback);
            fxmlLoader.setController(feedbackCellController);
            setText(null);

            try {setGraphic(fxmlLoader.load());}
            catch (IOException e) { e.printStackTrace();}
        }
    }
}
