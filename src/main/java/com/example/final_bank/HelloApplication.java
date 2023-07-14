package com.example.final_bank;

import com.example.final_bank.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        Model.get_model().get_view_manager().show_welcome_screen();
    }

    public static void main(String[] args)
    {
        launch();
    }
}