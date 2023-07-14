package com.example.final_bank.View_manager;

import javafx.concurrent.Task;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Initial_background_task extends Task<Void>
{
    @Override
    protected Void call() throws IOException
    {
        File file = new File(Fpath.data_path+"Data/Admin/abrar118@gmail.com");
        file.mkdirs();
        file = new File(Fpath.data_path+"Data/Client");
        file.mkdir();

        file = new File(Fpath.data_path+"Data/Admin/abrar118@gmail.com/name-pass.txt");
        file.createNewFile();

        FileWriter cout = new FileWriter(file);
        cout.write("abrar118@gmail.com\n");
        cout.write("hello\n");
        cout.write("1111\n");
        cout.write("Abrar Mahir Esam\n");
        cout.write("Male\n");
        cout.write("11-9-2001\n");
        cout.write("facebook.com/abrar118\n");
        cout.write("Mirpur DOHS\n");
        cout.close();

        InputStream inputStream = new URL("https://www.pngall.com/wp-content/uploads/12/Avatar-Profile-PNG-Photos.png").openStream();
        OutputStream outputStream = new FileOutputStream(Fpath.data_path+"Data/avatar.png");

        int len = 0, total = 0;
        while(len!=-1)
        {
            total+=len;
            updateProgress(total, 4121286);
            len = inputStream.read();
            outputStream.write(len);
        }

        inputStream = new FileInputStream(Fpath.data_path+"Data/avatar.png");
        Path path = Paths.get(Fpath.data_path+"Data/Admin/abrar118@gmail.com/avatar.png");
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

        return null;
    }
}
