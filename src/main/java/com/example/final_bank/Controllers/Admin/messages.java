package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Feedback;
import com.example.final_bank.Model.Message;
import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Feedback_cell_factory;
import com.example.final_bank.View_manager.Fpath;
import com.example.final_bank.View_manager.Message_cell_factory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class messages implements Initializable
{
    public Circle admin_pic;
    public Label admin_name;
    public ListView<Message> message_list;
    public Button message_button;
    public Button feedback_button;
    public AnchorPane message_pane;
    public AnchorPane feedback_pane;
    public Label warning;
    public ListView<Feedback> feedback_list;
    ObservableList<Message> messages;
    ObservableList<Feedback> feedbacks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        admin_name.setText(Model.get_model().current_admin.getName());
        admin_pic.setFill(new ImagePattern(Model.get_model().current_admin.get_profile_pic()));

        message_button.setOnMouseClicked(MouseEvent -> set_messages());
        feedback_button.setOnMouseClicked(MouseEvent -> set_feedback());

        message_list.getSelectionModel().selectedItemProperty().addListener((observableValue, old_value, new_value) -> expand_message(new_value));
        feedback_list.getSelectionModel().selectedItemProperty().addListener((observableValue, old_value, new_value) -> expand_feedback(new_value));
    }

    private void set_messages()
    {
        feedback_pane.setVisible(false);
        message_pane.setVisible(true);

        messages = FXCollections.observableArrayList();

        File file = new File(Fpath.data_path+"Data/Admin/abrar118@gmail.com/messages");
        try
        {
            File[] files = file.listFiles();
            Scanner sc;

            assert files != null;
            for(File f: files) {
                if (f.getName().equals("message no.txt")) continue;

                sc = new Scanner(f);
                StringBuilder s = new StringBuilder();

                while (sc.hasNextLine()) s.append(sc.nextLine()).append("\n");
                messages.add(new Message(s.toString()));
            }

            message_list.setItems(messages);
            message_list.setCellFactory(list -> new Message_cell_factory());

        }
        catch (IOException e) {
            file.mkdir();
        }
    }

    private void set_feedback()
    {
        feedback_pane.setVisible(true);
        message_pane.setVisible(false);

        feedbacks = FXCollections.observableArrayList();

        File file = new File(Fpath.data_path+"Data/Admin/Feedback/feedback.txt");
        try
        {
            int rating;
            StringBuilder body = new StringBuilder();
            String s;

            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                rating = sc.nextInt();
                while(sc.hasNextLine())
                {
                    s = sc.nextLine();
                    if(s.equals("end of message"))
                    {
                        feedbacks.add(new Feedback(body.toString(),rating));
                        body = new StringBuilder();
                        break;
                    }
                    else body.append(s).append("\n");
                }
            }

            feedback_list.setItems(feedbacks);
            feedback_list.setCellFactory(factory -> new Feedback_cell_factory());
        }
        catch (IOException e)
        {
            warning.setText("No feedback given");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event ->warning.setText("")));
            timeline.play();
        }

    }

    private void expand_message(Message new_value)
    {
        if(new_value==null) return;

        Label label = new Label(new_value.getMessage_body());
        AnchorPane anchorPane = new AnchorPane(label);

        ScrollPane scrollPane = new ScrollPane(anchorPane);
        scrollPane.setPrefHeight(100);
        scrollPane.setPrefWidth(550);

        try
        {
            Model.get_model().get_view_manager().create_stage(scrollPane);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void expand_feedback(Feedback new_value)
    {
        if(new_value==null) return;

        Label label = new Label(new_value.getBody());
        AnchorPane anchorPane = new AnchorPane(label);

        ScrollPane scrollPane = new ScrollPane(anchorPane);
        scrollPane.setPrefHeight(100);
        scrollPane.setPrefWidth(550);

        try
        {
            Model.get_model().get_view_manager().create_stage(scrollPane);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

