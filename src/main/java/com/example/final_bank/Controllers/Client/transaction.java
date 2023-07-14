package com.example.final_bank.Controllers.Client;

import com.example.final_bank.Model.Model;
import com.example.final_bank.Model.Transaction;
import com.example.final_bank.View_manager.Fpath;
import com.example.final_bank.View_manager.Transaction_cell_factory;
import com.example.final_bank.View_manager.View_manager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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

public class transaction implements Initializable
{
    public Button new_transaction_button;
    public Button view_history_button;
    public Rectangle new_transaction_rect;
    public FontAwesomeIconView new_transaction_icon;
    public Text new_transaction_text;
    public AnchorPane new_transaction_pane;
    public CheckBox use_savings_account;
    public TextField recipient;
    public TextField transaction_amount;
    public TextField message;
    public Label recipient_name;
    public Label receipt_amount;
    public Label charge;
    public Label discount;
    public Label total;
    public Label date;
    public Label transaction_successful_message;
    public Button make_transaction_button;
    public ListView<Transaction> history_list;
    public Label checking_balance;
    public Label savings_balance;
    private StringProperty observable_recipient;
    private StringProperty observable_amount;

    private ObservableList<Transaction> transactions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        view_history_button.setOnMouseClicked(MouseEvent -> view_history());
        new_transaction_button.setOnMouseClicked(MouseEvent-> new_transaction_button_clicked());

        new_transaction_icon.setOnMouseClicked(MouseEvent -> new_transaction_icon_clicked());
        make_transaction_button.setOnMouseClicked(MouseEvent -> transaction_warning_message());

        observable_recipient = new SimpleStringProperty("");
        observable_amount = new SimpleStringProperty("");
        recipient.setOnKeyTyped(KeyEvent -> change_recipient());
        transaction_amount.setOnKeyTyped(KeyEvent -> change_amount());

        observable_recipient.addListener((observableValue, old_val, new_val) -> change_receipt(new_val));
        observable_amount.addListener((observableValue, old_val, new_val) -> change_receipt_amount(new_val));

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

            write_to_recipient();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void write_to_recipient()
    {
        try
        {
            File file = new File(Fpath.data_path+"Data/Client/"+recipient.getText()+"/checking.txt");
            Scanner sc= new Scanner(file);
            double balance = sc.nextDouble();
            balance+=Double.parseDouble(transaction_amount.getText());
            sc.close();

            FileWriter print = new FileWriter(file);
            print.write(String.valueOf(balance));
            print.close();
        }
        catch (IOException e)
        {
            transaction_successful_message.setText("Account not found");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->transaction_successful_message.setText("")));
            timeline.play();
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

            write_to_recipient();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void change_recipient()
    {
        observable_recipient.set(recipient.getText());
    }

    private void change_receipt(String new_val)
    {
        recipient_name.setText(new_val);
    }

    private void change_amount()
    {
        observable_amount.set(transaction_amount.getText());
    }

    private void change_receipt_amount(String new_val)
    {
        receipt_amount.setText("$"+new_val);

        if(!transaction_amount.getText().isBlank())
        {
            try{
                double amount = Double.parseDouble(new_val);
                double final_amount = (amount+(amount*0.05))-(amount*0.03);
                total.setText(String.valueOf(final_amount));
                return;
            }
            catch (NumberFormatException e)
            {
                transaction_successful_message.setText(e.getMessage());
                return;
            }
        }

        total.setText("");
    }
    private void view_history()
    {
        new_transaction_pane.setVisible(false);
        history_list.setVisible(true);

        transactions = FXCollections.observableArrayList();
        set_items();
        history_list.setItems(transactions);
        history_list.setCellFactory(list_view -> new Transaction_cell_factory());
    }

    private void set_items()
    {
        transactions.clear();

        try {
            File file = new File(Fpath.data_path + "Data/Client/" + Model.get_model().current_client.getMail() + "/transaction.txt");
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
                transactions.add(transaction);
            }
            sc.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void new_transaction_button_clicked()
    {
        history_list.setVisible(false);
        new_transaction_pane.setVisible(false);

        new_transaction_icon.setVisible(true);
        new_transaction_rect.setVisible(true);
        new_transaction_text.setVisible(true);

        recipient.clear();
        transaction_amount.clear();
        message.clear();
    }

    private void transaction_warning_message()
    {
        if(!recipient.getText().isBlank() && !recipient.getText().equals(Model.get_model().current_client.getMail()) && !transaction_amount.getText().isBlank())
        {
            if(!use_savings_account.isSelected())
            {
                if(Double.parseDouble(checking_balance.getText()) < Double.parseDouble(total.getText()))
                {
                    transaction_successful_message.setText("Insufficient amount.");
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->transaction_successful_message.setText("")));
                    timeline.play();
                    return;
                }

                double balance = Double.parseDouble(checking_balance.getText()) - Double.parseDouble(total.getText());
                Model.get_model().checking_account.setBalance(balance);
            }
            else
            {
                if(Double.parseDouble(savings_balance.getText()) < Double.parseDouble(total.getText()))
                {
                    transaction_successful_message.setText("Insufficient amount.");
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->transaction_successful_message.setText("")));
                    timeline.play();
                    return;
                }

                double balance = Double.parseDouble(savings_balance.getText()) - Double.parseDouble(total.getText());
                Model.get_model().savings_account.setBalance(balance);
            }

            try
            {
                File file = new File(Fpath.data_path+"Data/Client/"+ Model.get_model().current_client.getMail()+"/transaction.txt");
                file.createNewFile();

                FileWriter cout = new FileWriter(file,true);
                cout.write("true\n");
                cout.write(recipient.getText()+"\n");
                cout.write(transaction_amount.getText()+"\n");
                cout.write(message.getText()+"\n");

                String time = Model.get_model().get_view_manager().get_date_time();
                cout.write(time+"\n");
                cout.close();

                if(transactions == null) transactions = FXCollections.observableArrayList();
                Transaction temp = new Transaction(recipient.getText(), Double.parseDouble(transaction_amount.getText()), message.getText(), time, true);
                transactions.add(temp);
                dashboard.transactions.add(temp);

                file = new File(Fpath.data_path+"Data/Client/"+recipient.getText()+"/transaction.txt");
                file.createNewFile();

                FileWriter cout1 = new FileWriter(file, true);
                cout1.write("false\n");
                cout1.write(Model.get_model().current_client.getMail()+"\n");
                cout1.write(transaction_amount.getText()+"\n");
                cout1.write(message.getText()+"\n");
                cout1.write(time+"\n");
                cout1.close();
            }
            catch (Exception e)
            {
                transaction_successful_message.setText("Transaction failed.");
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->transaction_successful_message.setText("")));
                timeline.play();

                recipient.clear();
                transaction_amount.clear();
                message.clear();
                return;
            }


            transaction_successful_message.setText("Transaction successful");
            transaction_successful_message.setStyle("-fx-text-fill: #0ec320");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->transaction_successful_message.setText("")));
            timeline.play();

            recipient.clear();
            transaction_amount.clear();
            message.clear();
        }
        else
        {
            transaction_successful_message.setText("Fill out recipient and amount.");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event->transaction_successful_message.setText("")));
            timeline.play();
        }
    }

    private void new_transaction_icon_clicked()
    {
        new_transaction_icon.setVisible(false);
        new_transaction_rect.setVisible(false);
        new_transaction_text.setVisible(false);

        new_transaction_pane.setVisible(true);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), new_transaction_pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        date.setText(today);
    }
}
