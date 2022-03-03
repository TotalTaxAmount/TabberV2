package Client.command;

import Client.utils.ConsoleColor;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface ICommand {
    void run(ArrayList<String> args, BufferedReader receive, PrintWriter send);
}
