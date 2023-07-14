package com.example.final_bank.Controllers.Admin;

import com.example.final_bank.Model.Log_in;
import com.example.final_bank.View_manager.Fpath;
import com.example.final_bank.View_manager.Log_in_cell_factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Log_in_activity implements Initializable
{
    public ListView<Log_in> log_in_list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        set_log_ins();
    }

    private void set_log_ins()
    {
        ObservableList<Log_in> logIns = FXCollections.observableArrayList();

        try
        {
            File file = new File(Fpath.data_path+"Data/Admin/log-in-history.txt");
            file.createNewFile();
            Scanner sc = new Scanner(file);
            String name, time;

            while(sc.hasNextLine())
            {
                name = sc.nextLine();
                time = sc.nextLine();

                logIns.add(new Log_in(name,time));
            }

            log_in_list.setItems(logIns);
            log_in_list.setCellFactory(factory -> new Log_in_cell_factory());
            sc.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
