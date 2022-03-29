package Client.command.commands;

import Client.command.ICommand;
import Client.utils.ConsoleColor;
import Client.utils.Network;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Info implements ICommand {

    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        send.println(ConsoleColor.ANSI_RESET + "Basic Info");
        send.println("-----------------------");
        send.println("  Username: " + System.getProperty("user.name"));
        send.println("  OS: " + System.getProperty("os.name"));
        send.println("  OS Version: " + System.getProperty("os.version"));
        send.println("  Java Version: " + System.getProperty("java.version"));
        send.println("-----------------------");
        send.println(ConsoleColor.ANSI_RESET + "Network");
        send.println("-----------------------");
        send.println("  Public IP: " + Network.getPublicIp());
        send.println("  Local IP: " + Network.getLocalIp());
        send.println("  Hostname: " + Network.getHostname());
        send.println("  MAC Address: " + Network.getMAC());
        send.println("-----------------------");
        send.println(ConsoleColor.ANSI_RESET + "Location");
        send.println("-----------------------");
        send.println("  Country: " + Network.getCountry());
        send.println("  City: " + Network.getCity(Network.getPublicIp()));

        send.println("done");
    }


}
