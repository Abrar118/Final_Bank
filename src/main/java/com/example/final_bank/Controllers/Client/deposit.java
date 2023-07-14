package com.example.final_bank.Controllers.Client;

import com.example.final_bank.Model.Deposit;
import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Deposit_cell_factory;
import com.example.final_bank.View_manager.Fpath;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

public class deposit implements Initializable
{
    public Button make_deposit_button;
    public Button view_history_button;
    public AnchorPane progress_pane_1;
    public Line progress_line_1;
    public AnchorPane progress_pane_2;
    public Line progress_line_2;
    public AnchorPane progress_pane_3;
    public AnchorPane amount_pane;
    public TextField deposit_amount;
    public Button proceed_to_reference_button;
    public AnchorPane reference_pane;
    public TextField account_reference;
    public Button proceed_to_receipt_button;
    public AnchorPane receipt;
    public Label date;
    public Label total_amount;
    public Label receipt_deposit;
    public Label receipt_reference;
    public Button confirm_deposit_button;
    public ListView<Deposit> history_list;
    public Label success_message;
    public Label checking_balance;
    public Label savings_balance;
    public CheckBox savings_deposit;
    public Button convert_currency;
    private ObservableList<Deposit> deposits;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        view_history_button.setOnMouseClicked(MouseEvent -> show_list());
        make_deposit_button.setOnMouseClicked(MouseEvent -> make_deposit());
        convert_currency.setOnMouseClicked(MouseEvent -> {try {Model.get_model().get_view_manager().show_converter();}
        catch (IOException e) {throw new RuntimeException(e);}});

        proceed_to_reference_button.setOnMouseClicked(MouseEvent -> proceed_to_reference());
        proceed_to_receipt_button.setOnMouseClicked(MouseEvent -> proceed_to_receipt());

        confirm_deposit_button.setOnMouseClicked(MouseEvent -> confirm_button_clicked());

        make_deposit();

        checking_balance.setText(String.valueOf(Model.get_model().checking_account.getBalance()));
        savings_balance.setText(String.valueOf(Model.get_model().savings_account.getBalance()));
        Model.get_model().savings_account.balanceProperty().addListener((observableValue, old_value, new_value) -> change_saving(new_value));
        Model.get_model().checking_account.balanceProperty().addListener((observableValue, old_value, new_value) -> change_checking(new_value));
    }

    private void change_checking(Number new_value)
    {
        checking_balance.setText(String.valueOf(new_value));

        try
        {
            File file = new File(Fpath.data_path+"Data/Client/"+Model.get_model().current_client.getMail()+"/checking.txt");
            FileWriter cout = new FileWriter(file);
            cout.write(checking_balance.getText());
            cout.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void change_saving(Number new_value)
    {
        savings_balance.setText(String.valueOf(new_value));

        try
        {
            File file = new File(Fpath.data_path+"Data/Client/"+Model.get_model().current_client.getMail()+"/savings.txt");
            FileWriter cout = new FileWriter(file);
            cout.write(savings_balance.getText());
            cout.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void show_list()
    {
        history_list.setVisible(true);

        deposits = FXCollections.observableArrayList();
        set_items();
        history_list.setItems(deposits);
        history_list.setCellFactory(depositListView -> new Deposit_cell_factory());
    }

    private void set_items()
    {
        deposits.clear();

        try
        {
            File file = new File(Fpath.data_path+"Data/Client/"+Model.get_model().current_client.getMail()+"/deposit.txt");
            file.createNewFile();
            Scanner sc = new Scanner(file);

            double amount;
            String reference;
            String time;

            while(sc.hasNext())
            {
                amount = sc.nextDouble();
                sc.nextLine();
                reference = sc.nextLine();
                time = sc.nextLine();

                Deposit deposit = new Deposit(amount,reference,time);
                deposits.add(deposit);
            }

            sc.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void make_deposit()
    {
        history_list.setVisible(false);

        progress_pane_1.setStyle("-fx-background-color: linear-gradient(to left, #252a7c, #303696, #3f48cc)");
        progress_line_1.setStyle("-fx-stroke: #bd382e");

        progress_pane_2.setStyle("-fx-background-color: linear-gradient(to left, #6d201b,#9c2e26, #bd382e)");
        progress_line_2.setStyle("-fx-stroke: #bd382e");

        progress_pane_3.setStyle("-fx-background-color: linear-gradient(to left, #6d201b,#9c2e26, #bd382e)");

        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(500), amount_pane);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), amount_pane);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition1, scaleTransition);
        parallelTransition.play();

        reference_pane.setScaleY(0);
        reference_pane.setScaleX(0);

        receipt.setScaleY(0);
        receipt.setScaleX(0);

        confirm_deposit_button.setVisible(false);

        success_message.setText("");
        deposit_amount.clear();
        account_reference.clear();
    }

    private void proceed_to_reference()
    {
        receipt_deposit.setText(deposit_amount.getText());

        progress_line_1.setStyle("-fx-stroke: #3f48cc");
        progress_pane_2.setStyle("-fx-background-color: linear-gradient(to left, #252a7c, #303696, #3f48cc)");

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), amount_pane);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);
        scaleTransition.play();

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(500), reference_pane);
        scaleTransition1.setToX(1);
        scaleTransition1.setToY(1);
        scaleTransition1.play();
    }

    private void proceed_to_receipt()
    {
        receipt_reference.setText(account_reference.getText());

        if(!receipt_deposit.getText().isBlank())
        {
            double money = Double.parseDouble(receipt_deposit.getText());
            money = money - (money*0.02);
            total_amount.setText(String.valueOf(money));
        }

        String date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        date.setText(date1);

        progress_line_2.setStyle("-fx-stroke: #3f48cc");
        progress_pane_3.setStyle("-fx-background-color: linear-gradient(to left, #252a7c, #303696, #3f48cc)");

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(500), reference_pane);
        scaleTransition1.setToX(0);
        scaleTransition1.setToY(0);
        scaleTransition1.play();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event->
        {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), receipt);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();

            confirm_deposit_button.setVisible(true);
        }));
        timeline.play();
    }

    private void confirm_button_clicked()
    {
        if(!deposit_amount.getText().isBlank() && !account_reference.getText().isBlank())
        {
            if(!savings_deposit.isSelected())
            {
                double balance = Double.parseDouble(total_amount.getText()) + Double.parseDouble(checking_balance.getText());
                Model.get_model().checking_account.setBalance(balance);
            }
            else
            {
                double balance = Double.parseDouble(total_amount.getText()) + Double.parseDouble(savings_balance.getText());
                Model.get_model().savings_account.setBalance(balance);
            }


            try
            {
                File file = new File(Fpath.data_path+"Data/Client/"+ Model.get_model().current_client.getMail()+"/deposit.txt");
                file.createNewFile();

                FileWriter cout = new FileWriter(file, true);
                cout.write(deposit_amount.getText()+"\n");
                cout.write(account_reference.getText()+"\n");

                String time = Model.get_model().get_view_manager().get_date_time();
                cout.write(time+"\n");
                cout.close();

                double amount = Double.parseDouble(deposit_amount.getText());
                String reference = account_reference.getText();

                if(deposits==null) deposits = FXCollections.observableArrayList();
                Deposit temp = new Deposit(amount,reference, time);
                deposits.add(temp);
                dashboard.deposits.add(temp);
            }
            catch (IOException exception)
            {
                success_message.setText(exception.getMessage());
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->make_deposit()));
                timeline.play();
            }

            success_message.setText("Deposit Successful");
        }
        else
        {
            success_message.setText("Deposit unsuccessful.");

        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->make_deposit()));
        timeline.play();
    }
}
