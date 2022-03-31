package Client.command.commands;

import Client.command.CMDFactory;
import Client.command.Command;
import Client.utils.Prefixes;

import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Help extends Command {
    public Help() {
        super("Help", "Displays a list of commands", "help");
    }


    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        try {
            CMDFactory cmdFactory = new CMDFactory();
            StringBuilder sb = new StringBuilder();
            for (Command c : cmdFactory.getCommands()) {
                sb.append(Prefixes.INFO)
                        .append(c.getName())
                        .append(" - ")
                        .append(c.getDescription())
                        .append("\n");
            }
            send.println(sb);
        } catch (Exception ignored) {}
    }
}
