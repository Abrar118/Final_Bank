package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Client;
import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Client_cell_factory;
import com.example.final_bank.View_manager.Fpath;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Clientele implements Initializable
{
    public ListView<Client> customer_list;
    public AnchorPane customer_details_pane;
    public AnchorPane account_creation_pane;
    public TextField first_name;
    public TextField last_name;
    public DatePicker date_of_birth;
    public PasswordField password;
    public PasswordField corfirm_password;
    public RadioButton male;
    public ToggleGroup gender;
    public RadioButton other;
    public TextField email_id;
    public TextField facebook_id;
    public TextField home_address;
    public FontAwesomeIconView email_id_icon;
    public FontAwesomeIconView fb_id_icon;
    public FontAwesomeIconView home_address_icon;
    public RadioButton agree_to_policy;
    public Text terms_and_condition;
    public Text rest_of_the_message;
    public Button create_account_button;
    public Label success_message;
    public Button all_clients_button;
    public Button add_client_button;
    public RadioButton female;
    public Button back_button;
    public Label personal_info_text;
    public Text remove_client;
    private ObservableList<Client> clients;
    public StringProperty search_bar_text =new SimpleStringProperty("");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        StringProperty option_clicked = new SimpleStringProperty("all-client");
        option_clicked.addListener((observableValue, old_value, new_value) -> change_option(new_value));

        create_account_button.setText("Next");
        create_account_button.setOnMouseClicked(MouseEvent -> create_button_clicked());

        show_all_client();

        all_clients_button.setOnMouseClicked(MouseEvent -> option_clicked.set("all-client"));
        add_client_button.setOnMouseClicked(MouseEvent -> option_clicked.set("new-account"));
        remove_client.setOnMouseClicked(MouseEvent -> delete_account());

        back_button.setOnMouseClicked(MouseEvent -> back_to_personal_info());

        customer_list.setOnMouseClicked(MouseEvent -> client_cell_clicked());

        search_bar_text.bind(top_bar_controller.search_text.textProperty());
        search_bar_text.addListener((observableValue, old_value, new_value) -> filter_clients(new_value));
    }

    private void change_option(String new_value)
    {
        switch (new_value)
        {
            case "all-client" -> show_all_client();
            case "new-account" -> create_new_account();
        }
    }

    private void create_new_account()
    {
        customer_list.setVisible(false);
        customer_details_pane.setVisible(false);
        account_creation_pane.setVisible(true);
        create_account_button.setText("Next");

        first_name.setVisible(true);
        last_name.setVisible(true);
        password.setVisible(true);
        corfirm_password.setVisible(true);
        date_of_birth.setVisible(true);
        male.setVisible(true);
        female.setVisible(true);
        other.setVisible(true);

        email_id.setVisible(false);
        email_id_icon.setVisible(false);
        facebook_id.setVisible(false);
        fb_id_icon.setVisible(false);
        home_address.setVisible(false);
        home_address_icon.setVisible(false);
        back_button.setVisible(false);
        agree_to_policy.setVisible(false);
        terms_and_condition.setVisible(false);
        rest_of_the_message.setVisible(false);
        success_message.setText("");
    }

    private void show_all_client()
    {
        account_creation_pane.setVisible(false);
        customer_details_pane.setVisible(true);
        customer_list.setVisible(true);

        clients = FXCollections.observableArrayList();
        set_clients();
        customer_list.setItems(clients);
        customer_list.setCellFactory(customers -> new Client_cell_factory());
    }

    private void set_clients()
    {
        clients.clear();

        try
        {
            File file = new File(Fpath.data_path+"Data/Client");
            File[] customers = file.listFiles();

            assert customers != null;
            for(File f: customers)
            {
                File temp_file = new File(Fpath.data_path+"Data/Client/"+f.getName()+"/name-pass.txt");
                Scanner sc = new Scanner(temp_file);

                String mail = sc.next();
                String pass = sc.next();
                sc.nextLine();
                String name = sc.nextLine();
                String gender = sc.next();
                String birth = sc.next();
                sc.nextLine();
                String facebook = sc.nextLine();
                String home = sc.nextLine();

                Client client = new Client(mail, pass, name, gender, birth, facebook, home);
                clients.add(client);
                sc.close();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void create_button_clicked()
    {
        if(create_account_button.getText().equals("Next"))
        {
            if(!first_name.getText().isBlank() && !last_name.getText().isBlank() && !password.getText().isBlank() && !corfirm_password.getText().isBlank()
                    && !date_of_birth.getValue().toString().isBlank() && (male.isSelected() || female.isSelected() || other.isSelected()))
            {
                if(password.getText().equals(corfirm_password.getText()))
                {
                    first_name.setVisible(false);
                    last_name.setVisible(false);
                    password.setVisible(false);
                    corfirm_password.setVisible(false);
                    date_of_birth.setVisible(false);
                    male.setVisible(false);
                    female.setVisible(false);
                    other.setVisible(false);

                    email_id.setVisible(true);
                    email_id_icon.setVisible(true);
                    facebook_id.setVisible(true);
                    fb_id_icon.setVisible(true);
                    home_address.setVisible(true);
                    home_address_icon.setVisible(true);
                    back_button.setVisible(true);
                    agree_to_policy.setVisible(true);
                    terms_and_condition.setVisible(true);
                    rest_of_the_message.setVisible(true);
                    success_message.setVisible(true);

                    create_account_button.setText("Create Account");
                    personal_info_text.setText("Social Information");
                    success_message.setText("");
                }
                else
                {
                    success_message.setStyle("-fx-text-fill: #a81e14");
                    success_message.setText("Passwords don't match");
                }
            }
            else
            {
                success_message.setStyle("-fx-text-fill: #a81e14");
                success_message.setText("Fill out all the fields to proceed.");
            }
        }
        else if(create_account_button.getText().equals("Create Account"))
        {
            if(!email_id.getText().isBlank() && !facebook_id.getText().isBlank() && !home_address.getText().isBlank())
            {
                if(agree_to_policy.isSelected())
                {
                    try
                    {
                        File file = new File(Fpath.data_path+"Data/Client/"+email_id.getText());
                        if(file.exists())
                        {
                            success_message.setText("Account Already exists");
                            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event->success_message.setText("")));
                            timeline.play();
                            return;
                        }

                        create_client_file();

                        success_message.setStyle("-fx-text-fill: green");
                        success_message.setText("Account Creation Successful.");

                        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event ->
                        {
                            create_account_button.setText("Next");

                            first_name.setVisible(true); first_name.clear();
                            last_name.setVisible(true); last_name.clear();
                            password.setVisible(true); password.clear();
                            corfirm_password.setVisible(true); corfirm_password.clear();
                            date_of_birth.setVisible(true); date_of_birth.setValue(null);
                            male.setVisible(true); male.setSelected(false);
                            female.setVisible(true); female.setSelected(false);
                            other.setVisible(true); other.setSelected(false);

                            email_id.setVisible(false); email_id.clear();
                            email_id_icon.setVisible(false);
                            facebook_id.setVisible(false); facebook_id.clear();
                            fb_id_icon.setVisible(false);
                            home_address.setVisible(false); home_address.clear();
                            home_address_icon.setVisible(false);
                            back_button.setVisible(false);
                            agree_to_policy.setVisible(false);
                            terms_and_condition.setVisible(false);
                            rest_of_the_message.setVisible(false);
                            success_message.setVisible(false);
                        }));
                        timeline.setCycleCount(1);
                        timeline.play();
                    }
                    catch (IOException e)
                    {
                        success_message.setStyle("-fx-text-fill: #a81e14");
                        success_message.setText("Account Creation Failed");
                    }
                }
                else
                {
                    success_message.setStyle("-fx-text-fill: #a81e14");
                    success_message.setText("Agree to terms and condition to proceed.");
                }
            }
            else
            {
                success_message.setStyle("-fx-text-fill: #a81e14");
                success_message.setText("Fill out all the fields to proceed.");
            }
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event->success_message.setText("")));
        timeline.play();
    }

    private void back_to_personal_info()
    {
        first_name.setVisible(true);
        last_name.setVisible(true);
        password.setVisible(true);
        corfirm_password.setVisible(true);
        date_of_birth.setVisible(true);
        male.setVisible(true);
        female.setVisible(true);
        other.setVisible(true);

        email_id.setVisible(false);
        email_id_icon.setVisible(false);
        facebook_id.setVisible(false);
        fb_id_icon.setVisible(false);
        home_address.setVisible(false);
        home_address_icon.setVisible(false);
        back_button.setVisible(false);
        agree_to_policy.setVisible(false);
        terms_and_condition.setVisible(false);
        rest_of_the_message.setVisible(false);
        success_message.setVisible(false);

        create_account_button.setText("Next");
        personal_info_text.setText("Personal Information");
    }

    private void create_client_file() throws IOException
    {
        File file = new File(Fpath.data_path+"/Data/Client/"+email_id.getText());
        boolean exists=file.mkdirs();


        file = new File(Fpath.data_path+"/Data/Client/"+email_id.getText()+"/name-pass.txt");
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(email_id.getText()+"\n");
        fileWriter.write(password.getText()+"\n");
        fileWriter.write(first_name.getText()+" "+last_name.getText()+"\n");
        fileWriter.write(((RadioButton)(gender.getSelectedToggle())).getText()+"\n");
        fileWriter.write(date_of_birth.getValue()+"\n");
        fileWriter.write(facebook_id.getText()+"\n");
        fileWriter.write(home_address.getText()+"\n");
        fileWriter.close();

        InputStream inputStream = new FileInputStream(Fpath.data_path+"Data/avatar.png");
        Path path = Paths.get(Fpath.data_path+"Data/Client/"+ email_id.getText() +"/avatar.png");

        Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();

        file = new File(Fpath.data_path+"/Data/Client/"+email_id.getText()+"/checking.txt");
        FileWriter cout = new FileWriter(file);
        cout.write("1000");
        cout.close();

        file = new File(Fpath.data_path+"/Data/Client/"+email_id.getText()+"/savings.txt");
        FileWriter cout1 = new FileWriter(file);
        cout1.write("5000");
        cout1.close();

        Client client = new Client(email_id.getText(), password.getText(), first_name.getText()+last_name.getText(), ((RadioButton)(gender.getSelectedToggle())).getText(), String.valueOf(date_of_birth.getValue()), facebook_id.getText(), home_address.getText());
        clients.add(client);
    }

    private void delete_account()
    {
        if(customer_list.getSelectionModel().getSelectedItem()==null) return;

        Client client = customer_list.getSelectionModel().getSelectedItem();

        for(int i=0;i<clients.size();++i)
        {
            if(clients.get(i).getMail().equals(client.getMail())) clients.remove(clients.get(i));
        }

        File file = new File(Fpath.data_path+"Data/Client/"+client.getMail());
        File[] ls = file.listFiles();

        assert ls != null;
        for(File f:ls) f.delete();

        file.delete();
    }

    private void client_cell_clicked()
    {
        Model.get_model().selected_client = customer_list.getSelectionModel().getSelectedItem();
        if(Model.get_model().selected_client==null) return;

        try{Model.get_model().get_view_manager().show_client_details();}
        catch (IOException e) {e.printStackTrace();}
    }

    private void filter_clients(String new_value)
    {
        if(new_value.isBlank())
        {
            customer_list.setItems(clients);
            return;
        }

        ObservableList<Client> temp_list = FXCollections.observableArrayList();
        for(Client client: clients)
        {
            if(client.getMail().contains(new_value) || client.getName().contains(new_value)) temp_list.add(client);
        }

        customer_list.setItems(temp_list);
    }
}
