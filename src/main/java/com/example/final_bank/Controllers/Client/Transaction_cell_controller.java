package com.example.final_bank.Controllers.Client;

import com.example.final_bank.HelloApplication;
import com.example.final_bank.Model.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Transaction_cell_controller implements Initializable
{
    public FontAwesomeIconView receive_arrow;
    public FontAwesomeIconView send_arrow;
    public Label receiver;
    public Label amount;
    public FontAwesomeIconView message_icon;
    public Label time;

    private final Transaction transaction;

    public Transaction_cell_controller(Transaction transaction)
    {
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(transaction.isType()) receive_arrow.setVisible(false);
        else send_arrow.setVisible(false);

        receiver.setText(transaction.getReceiver());
        amount.setText(String.valueOf(transaction.getAmount()));
        time.setText(transaction.getTime());

        message_icon.setOnMouseClicked(MouseEvent -> show_message_pane());
    }

    private void show_message_pane()
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10,10,10,10));

        FontAwesomeIconView iconView = new FontAwesomeIconView();
        iconView.setGlyphName("COMMENT");
        iconView.setStyle("-glyph-size: 30");
        vBox.getChildren().add(iconView);

        Label message = new Label(transaction.getMessage());
        message.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
        vBox.getChildren().add(message);

        Scene scene = new Scene(vBox, 400,200);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MATBank");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("images/logo.png")).toString()));
        stage.show();
    }
}
