package com.example.final_bank.Controllers.Client;

import com.example.final_bank.Model.Model;
import com.example.final_bank.View_manager.Fpath;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class top_panel implements Initializable
{
    public Circle avatar;
    public TextField search_bar;
    public FontAwesomeIconView search_icon;
    public Label client_name;
    public Text Accounts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        search_icon.setOnMouseClicked(MouseEvent->search_clicked());
        client_name.setText(Model.get_model().current_client.getName());
        Model.get_model().current_client.get_name_property().addListener((observableValue, old_value, new_value) -> client_name.setText(new_value));
        Accounts.setOnMouseClicked(MouseEvent-> Model.get_model().get_view_manager().get_customer_option_clicked().set("Profile"));

        avatar.setOnMouseClicked(MouseEvent -> profile_pic_change());

        avatar.setFill(new ImagePattern(Model.get_model().current_client.get_profile_pic()));
    }

    private void profile_pic_change()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\Downloads"));
        File file = fileChooser.showOpenDialog(new Stage());

        if(file!=null)
        {
            String path = file.getPath();
            avatar.setFill(new ImagePattern(new Image(path)));

            try
            {
                InputStream in = new FileInputStream(path);
                Path target = Paths.get(Fpath.data_path+"Data/Client/"+Model.get_model().current_client.getMail()+"/avatar.png");
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void search_clicked()
    {
        Model.get_model().get_view_manager().get_customer_option_clicked().set(search_bar.getText());
        search_bar.clear();
    }
}
