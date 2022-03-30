package Client.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Command {
    private final String name;
    private final String description;
    private final String syntax;

    public Command(String name, String description, String syntax) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
    }

    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send){}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSyntax() {
        return syntax;
    }
}
