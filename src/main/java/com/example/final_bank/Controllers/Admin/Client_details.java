package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Client;
import com.example.final_bank.Model.Deposit;
import com.example.final_bank.Model.Model;
import com.example.final_bank.Model.Transaction;
import com.example.final_bank.View_manager.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Client_details implements Initializable
{
    public ListView<Transaction> transaction_list;
    public ListView<Deposit> deposit_list;
    private Client client;
    public AnchorPane about_pane;
    public ImageView profile_pic;
    public Label profile_name;
    public Label email;
    public Label bday;
    public Label home_address;
    public Label facebook;
    public Text transactions;
    public Text about;
    public ObservableList<Transaction> transactionObservableList;
    public ObservableList<Deposit> depositObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        client = Model.get_model().selected_client;
        set_view();
        set_lists();

        set_listeners();
    }

    private void set_listeners()
    {
        about.setOnMouseClicked(mouseEvent -> {about_pane.setVisible(true); transaction_list.setVisible(false); deposit_list.setVisible(false);});
        transactions.setOnMouseClicked(mouseEvent -> {about_pane.setVisible(false); transaction_list.setVisible(true); deposit_list.setVisible(true);});
    }

    private void set_view()
    {
        profile_name.setText(client.getName());
        email.setText(client.getMail());
        bday.setText(client.getBirthdate());
        home_address.setText(client.getHome_address());
        facebook.setText(client.getFacebook());
        profile_pic.setImage(new Image(Fpath.data_path+"Data/Client/"+client.getMail()+"/avatar.png"));

        about_pane.setVisible(true);
        transaction_list.setVisible(false);
        deposit_list.setVisible(false);
    }

    private void set_lists()
    {
        transactionObservableList = FXCollections.observableArrayList();
        depositObservableList = FXCollections.observableArrayList();

        try
        {
            File file = new File(Fpath.data_path + "Data/Client/" + client.getMail() + "/transaction.txt");
            file.createNewFile();
            Scanner sc = new Scanner(file);

            boolean type;
            String receiver;
            double amount;
            String message;
            String time;

            while(sc.hasNext())
            {
                type = sc.next().equals("true");
                receiver = sc.next();
                amount = sc.nextDouble();
                sc.nextLine();
                message = sc.nextLine();
                time = sc.nextLine();

                Transaction transaction = new Transaction(receiver, amount, message, time, type);
                transactionObservableList.add(transaction);
            }

            file = new File(Fpath.data_path+"Data/Client/"+client.getMail()+"/deposit.txt");
            file.createNewFile();
            sc = new Scanner(file);

            String reference;

            while(sc.hasNext())
            {
                amount = sc.nextDouble();
                sc.nextLine();
                reference = sc.nextLine();
                time = sc.nextLine();

                Deposit deposit = new Deposit(amount, reference, time);
                depositObservableList.add(deposit);
            }

            sc.close();

            transaction_list.setItems(transactionObservableList);
            transaction_list.setCellFactory(factory-> new Small_transaction_cell_factory());

            deposit_list.setItems(depositObservableList);
            deposit_list.setCellFactory(factory-> new Small_deposit_cell_factory());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
