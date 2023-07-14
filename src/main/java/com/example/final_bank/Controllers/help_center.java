package com.example.final_bank.Controllers;

import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Fpath;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class help_center implements Initializable
{
    public Button call_center_button;
    public Button give_feedback_button;
    public FontAwesomeIconView back_button;
    public AnchorPane helper_admin_pane;
    public Circle admin_pic;
    public Label admin_phone;
    public FontAwesomeIconView message_admin;
    public AnchorPane feedback_upper_pane;
    public AnchorPane one;
    public AnchorPane two;
    public AnchorPane three;
    public AnchorPane four;
    public AnchorPane five;
    public AnchorPane feedback_lower_pane;
    public Label feedback_rating;
    public TextArea feedback_message;
    public Button submit_button;
    public AnchorPane initial_text_pane;
    public Button send_admin_message_button;
    public TextArea send_admin_message_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        back_button.setOnMouseClicked(MouseEvent -> Model.get_model().get_view_manager().close_stage(back_button));

        show_text_pane();
        add_listeners();

        give_feedback_button.setOnMouseClicked(MouseEvent -> feedback_pane_appear());
        call_center_button.setOnMouseClicked(MouseEvent -> helper_admin_pane_appear());
        message_admin.setOnMouseClicked(MouseEvent -> send_message_to_admin());
        send_admin_message_button.setOnMouseClicked(MouseEvent -> send_admin_message_button_clicked());
        submit_button.setOnMouseClicked(MouseEvent -> submit_button_clicked());

        admin_pic.setFill(new ImagePattern(new Image(Fpath.data_path+"Data/Admin/abrar118@gmail.com/avatar.png")));
    }

    private void submit_button_clicked()
    {
        try
        {
            if(feedback_message.getText().isBlank()) return;

            File file = new File(Fpath.data_path+"Data/Admin/Feedback");
            file.mkdir();

            file = new File(Fpath.data_path+"Data/Admin/Feedback/feedback.txt");
            file.createNewFile();

            FileWriter cout = new FileWriter(file,true);
            cout.write(feedback_rating.getText()+"\n");
            cout.write(feedback_message.getText()+"\n");
            cout.write("end of message\n");
            cout.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        feedback_message.clear();
    }

    private void send_admin_message_button_clicked()
    {
        send_admin_message_button.setVisible(false);
        send_admin_message_field.setVisible(false);

        try
        {
            if(send_admin_message_field.getText().isBlank()) return;

            File file = new File(Fpath.data_path+"Data/Admin/abrar118@gmail.com/messages");
            file.mkdir();

            FileWriter cout;

            file = new File(Fpath.data_path+"Data/Admin/abrar118@gmail.com/messages/message no.txt");

            int message_count;
            if(file.createNewFile())
            {
                message_count = 0;
                System.out.println(message_count);
                cout = new FileWriter(file);
                cout.write(message_count +"\n");
                cout.close();
            }
            else
            {
                Scanner sc = new Scanner(file);
                message_count = sc.nextInt();
                sc.close();

                cout = new FileWriter(Fpath.data_path+"Data/Admin/abrar118@gmail.com/messages/message no.txt");
                cout.write(message_count+1+"\n");
                cout.close();
            }

            file = new File(Fpath.data_path+"Data/Admin/abrar118@gmail.com/messages/message"+ message_count +".txt");
            file.createNewFile();

            cout = new FileWriter(file);

            cout.write(send_admin_message_field.getText()+"\n");
            cout.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void send_message_to_admin()
    {
        send_admin_message_button.setVisible(true);
        send_admin_message_field.setVisible(true);

        send_admin_message_field.clear();
    }

    private void add_listeners()
    {
        one.setOnMouseClicked(MouseEvent -> feedback_rating.setText("1"));
        two.setOnMouseClicked(MouseEvent -> feedback_rating.setText("2"));
        three.setOnMouseClicked(MouseEvent -> feedback_rating.setText("3"));
        four.setOnMouseClicked(MouseEvent -> feedback_rating.setText("4"));
        five.setOnMouseClicked(MouseEvent -> feedback_rating.setText("5"));

        send_admin_message_button.setVisible(false);
        send_admin_message_field.setVisible(false);

        if(Model.get_model().current_client.getMail().isEmpty() && Model.get_model().current_admin.getMail().isEmpty()) give_feedback_button.setVisible(false);
        if(!Model.get_model().current_admin.getMail().isEmpty()) call_center_button.setVisible(false);
    }

    private void show_text_pane()
    {
        feedback_upper_pane.setScaleY(0);
        feedback_upper_pane.setScaleX(0);
        feedback_lower_pane.setScaleY(0);
        feedback_lower_pane.setScaleX(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), initial_text_pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), initial_text_pane);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, scaleTransition);
        parallelTransition.play();
    }

    private void helper_admin_pane_appear()
    {
        feedback_upper_pane.setScaleY(0);
        feedback_upper_pane.setScaleX(0);
        feedback_lower_pane.setScaleY(0);
        feedback_lower_pane.setScaleX(0);

        initial_text_pane.setScaleY(0);
        initial_text_pane.setScaleX(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), helper_admin_pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), helper_admin_pane);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, scaleTransition);
        parallelTransition.play();
    }

    private void feedback_pane_appear()
    {
        helper_admin_pane.setScaleY(0);
        helper_admin_pane.setScaleX(0);
        initial_text_pane.setScaleY(0);
        initial_text_pane.setScaleX(0);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(500), feedback_upper_pane);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(500), feedback_upper_pane);
        scaleTransition1.setToX(1);
        scaleTransition1.setToY(1);

        ParallelTransition parallelTransition1 = new ParallelTransition(fadeTransition1, scaleTransition1);
        parallelTransition1.play();


        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(500), feedback_lower_pane);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(500), feedback_lower_pane);
        scaleTransition2.setToX(1);
        scaleTransition2.setToY(1);

        ParallelTransition parallelTransition2 = new ParallelTransition(fadeTransition2, scaleTransition2);
        parallelTransition2.play();

        send_admin_message_button.setVisible(false);
        send_admin_message_field.setVisible(false);

        feedback_message.clear();
    }
}
