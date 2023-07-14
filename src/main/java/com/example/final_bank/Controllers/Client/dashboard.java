package com.example.final_bank.Controllers.Client;

import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Deposit;
import com.example.final_bank.Model.Model;
import com.example.final_bank.Model.Quick_link;
import com.example.final_bank.Model.Transaction;
import com.example.final_bank.View_manager.Fpath;
import com.example.final_bank.View_manager.Quick_link_cell_factory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class dashboard implements Initializable
{
    public static ObservableList<Transaction>transactions;
    public static ObservableList<Deposit>deposits;
    public Label ashol_name;
    public Label card_holder;
    public Label gold_credit;
    public Label also_card_holder;
    public Label silver_credit;
    public Label total_transaction;
    public Label total_deposit;
    public AnchorPane balance_pane;
    public AnchorPane checking_pane;
    public Label checking_balance;
    public AnchorPane savings_pane;
    public Label savings_balance;
    public FontAwesomeIconView right_arrow;
    public FontAwesomeIconView left_arrow;
    public ListView<Quick_link> quick_links;
    public LineChart<String,Double> transaction_line_chart;
    private ArrayList<AnchorPane> accounts_list;
    private IntegerProperty index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        set_view();
        set_quick_link();
        set_accounts();

        transactions.addListener((ListChangeListener<? super Transaction>) observable -> set_transaction());
        deposits.addListener((ListChangeListener<? super Deposit>) observable -> set_deposit());

        Model.get_model().checking_account.balanceProperty().addListener((observableValue, old_value, new_value) -> checking_balance.setText(String.valueOf(Math.round(Model.get_model(). checking_account.getBalance()))));
        Model.get_model().savings_account.balanceProperty().addListener((observableValue, old_value, new_value) -> savings_balance.setText(String.valueOf(Math.round(Model.get_model().savings_account.getBalance()))));
        
        index.addListener((observableValue, old_value, new_value) -> change_pane(new_value));
    }

    private void set_deposit()
    {
        double total = 0;
        for(Deposit deposit:deposits) total+=deposit.getAmount();
        total_deposit.setText(String.valueOf(total));
        set_chart();
    }

    private void set_transaction()
    {
        double total = 0;
        for(Transaction transaction:transactions) total+=transaction.getAmount();
        total_transaction.setText(String.valueOf(total));
        set_chart();
    }

    private void set_view() {
        if (transactions == null) transactions = FXCollections.observableArrayList();
        if (deposits == null) deposits = FXCollections.observableArrayList();

        transactions.clear();
        deposits.clear();

        String card = String.valueOf((int)((Math.random()*1000000)%10000));
        gold_credit.setText(card);
        silver_credit.setText(card);

        set_information();
        set_chart();
    }

    private void set_information()
    {
        card_holder.setText(Model.get_model().current_client.getName());
        ashol_name.setText(Model.get_model().current_client.getName());
        also_card_holder.setText(Model.get_model().current_client.getName());

        Model.get_model().current_client.get_name_property().addListener((observableValue, old_value, new_value) -> ashol_name.setText(new_value));


        double total = 0;
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

                total+=amount;
            }

            total_transaction.setText(String.valueOf(total));
            total =0;

            file = new File(Fpath.data_path+"Data/Client/"+Model.get_model().current_client.getMail()+"/deposit.txt");
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
                deposits.add(deposit);

                total+=amount;
            }

            total_deposit.setText(String.valueOf(total));

            sc.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void set_quick_link()
    {
        ObservableList<Quick_link> links = FXCollections.observableArrayList();

        Quick_link quickLink = new Quick_link();
        quickLink.setLink("https://www.bb.org.bd/en/index.php/econdata/exchangerate");
        quickLink.setLink_name("Bangladesh Bank");
        quickLink.setDescription("Exchange rates of Taka");
        quickLink.setLogo(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/1641869973.jpg")).toString()));
        links.add(quickLink);

        quickLink = new Quick_link();
        quickLink.setLink("https://www.marketwatch.com/");
        quickLink.setLink_name("Market Watch");
        quickLink.setDescription("Stock market prices");
        quickLink.setLogo(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/download.png")).toString()));
        links.add(quickLink);

        quickLink = new Quick_link();
        quickLink.setLink("https://www.worldbank.org/en/home");
        quickLink.setLink_name("Word Bank");
        quickLink.setDescription("Finances around the world");
        quickLink.setLogo(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/download (1).jpg")).toString()));
        links.add(quickLink);

        quickLink = new Quick_link();
        quickLink.setLink("https://www.independent24.com/");
        quickLink.setLink_name("Independent News");
        quickLink.setDescription("24/7 updates of the country");
        quickLink.setLogo(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/download (1).png")).toString()));
        links.add(quickLink);

        quick_links.setItems(links);
        quick_links.setCellFactory(cell -> new Quick_link_cell_factory());
    }

    private void set_accounts()
    {
        savings_balance.setText(String.valueOf(Math.round(Model.get_model().savings_account.getBalance())));
        checking_balance.setText(String.valueOf(Math.round(Model.get_model(). checking_account.getBalance())));

        accounts_list = new ArrayList<>();
        accounts_list.add(checking_pane);
        accounts_list.add(savings_pane);

        index = new SimpleIntegerProperty(0);
        balance_pane.getChildren().clear();
        balance_pane.getChildren().add(accounts_list.get(index.get()));
        
        right_arrow.setOnMouseClicked(MouseEvent -> index.set(index.get()==0? 1:0));
        left_arrow.setOnMouseClicked(MouseEvent -> index.set(index.get()==0? 1:0));
    }
    
    private void change_pane(Number new_value)
    {
        balance_pane.getChildren().clear();
        balance_pane.getChildren().add(accounts_list.get((int) new_value));
    }

    private void set_chart()
    {
        transaction_line_chart.getData().clear();

        XYChart.Series<String,Double> series = new XYChart.Series<>();
        series.setName("Transaction");

        for(int i=0;i<transactions.size();++i) series.getData().add(new XYChart.Data<>(String.valueOf(i+1),transactions.get(i).getAmount()));
        transaction_line_chart.getData().add(series);

        XYChart.Series<String,Double> series2 = new XYChart.Series<>();
        series2.setName("Deposit");

        for(int i=0;i<deposits.size();++i) series2.getData().add(new XYChart.Data<>(String.valueOf(i+1),deposits.get(i).getAmount()));
        transaction_line_chart.getData().add(series2);
    }
}
