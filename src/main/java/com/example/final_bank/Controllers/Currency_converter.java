package com.example.final_bank.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Currency_converter implements Initializable
{

    public Text converter;
    public Text stats;
    public AnchorPane converter_pane;
    public AnchorPane stat_pane;
    public Label date;
    public ChoiceBox<String> from_checkbox;
    public ChoiceBox<String> to_checkbox;
    public Label to_currency;
    public TextField from_currency;
    public Label dollar_rate;
    public Label pound_rate;
    public Label euro_rate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        dollar_rate.setText("102.97");
        pound_rate.setText("122.42");
        euro_rate.setText("106.53");

        from_checkbox.setItems(FXCollections.observableArrayList("USD","POUND", "EURO"));
        to_checkbox.setItems(FXCollections.observableArrayList("TAKA"));

        converter.setOnMouseClicked(MouseEvent -> show_converter());
        stats.setOnMouseClicked(MouseEvent -> show_stats());

        from_currency.textProperty().addListener((observableValue, old_value, new_value) -> change_currency(new_value));
    }

    private void change_currency(String new_value)
    {
        try
        {
            double currency = Double.parseDouble(new_value);
            switch (from_checkbox.getValue())
            {
                case "USD" -> to_currency.setText(String.valueOf(currency*Double.parseDouble(dollar_rate.getText()) - currency*0.03));
                case "POUND" -> to_currency.setText(String.valueOf(currency*Double.parseDouble(pound_rate.getText()) - currency*0.03));
                case "EURO" ->to_currency.setText(String.valueOf(currency*Double.parseDouble(euro_rate.getText()) - currency*0.03));
                default -> to_currency.setText("no input");
            }

        }
        catch (Exception e)
        {
            if(new_value.isBlank()) to_currency.setText("blank");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> to_currency.setText("")));
            timeline.play();
        }
    }

    private void show_stats()
    {
        converter_pane.setVisible(false);
        stat_pane.setVisible(true);
    }

    private void show_converter()
    {
        converter_pane.setVisible(true);
        stat_pane.setVisible(false);

        from_currency.clear();
    }
}
