package com.example.final_bank.Controllers.Client;

import com.example.final_bank.Model.Deposit;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Deposit_view_cell_controller implements Initializable
{

    public Label amount;
    public Label reference;
    public Label time;
    private final Deposit deposit;

    public Deposit_view_cell_controller(Deposit deposit)
    {
        this.deposit = deposit;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
//        amount.textProperty().bind(deposit.amountProperty().asString());
//        reference.textProperty().bind(deposit.referenceProperty());
//        time.textProperty().bind(deposit.timeProperty());

        amount.setText(String.valueOf(deposit.getAmount()));
        reference.setText(deposit.getReference());
        time.setText(deposit.getTime());
    }
}
