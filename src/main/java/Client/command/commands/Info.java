package Client.command.commands;

import Client.command.Command;
import Client.utils.ConsoleColor;
import Client.utils.Network;
import Client.utils.Prefixes;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Info extends Command {
    public Info() {
        super("Info", "Info about host machine", "info");
    }

    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        send.println(ConsoleColor.ANSI_RESET + "     Basic Info");
        send.println(Prefixes.INFO + "-----------------------");
        send.println(Prefixes.INFO + "  Username: " + System.getProperty("user.name"));
        send.println(Prefixes.INFO + "  OS: " + System.getProperty("os.name"));
        send.println(Prefixes.INFO + "  OS Version: " + System.getProperty("os.version"));
        send.println(Prefixes.INFO + "  Java Version: " + System.getProperty("java.version"));
        send.println(Prefixes.INFO + "-----------------------");
        send.println(ConsoleColor.ANSI_RESET + "    Network");
        send.println(Prefixes.INFO + "-----------------------");
        send.println(Prefixes.INFO + "  Public IP: " + Network.getPublicIp());
        send.println(Prefixes.INFO + "  Local IP: " + Network.getLocalIp());
        send.println(Prefixes.INFO + "  Hostname: " + Network.getHostname());
        send.println(Prefixes.INFO + "  MAC Address: " + Network.getMAC());
        send.println(Prefixes.INFO + "-----------------------");
        send.println(ConsoleColor.ANSI_RESET + "    Location");
        send.println(Prefixes.INFO + "-----------------------");
        send.println(Prefixes.INFO + "  Country: " + Network.getCountry());
        send.println(Prefixes.INFO + "  City: " + Network.getCity(Network.getPublicIp()));
        send.println(Prefixes.INFO + "-----------------------");


    }


}
