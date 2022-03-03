package Client.command.commands;

import Client.command.ICommand;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TestCommand implements ICommand {
    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        send.println("TestCmd");
    }
}
