package Client.command.commands;

import Client.command.Command;
import Client.utils.Prefixes;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.awt.*;
import java.util.ArrayList;

public class Tab extends Command {

    private final Robot robot = new Robot();

    public Tab() throws AWTException {
        super("Tab", "Tab's the hosts machine", "tab");
    }


    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        robot.keyPress(18);
        robot.keyPress(9);
        robot.keyRelease(18);
        robot.keyRelease(9);
        send.println(Prefixes.SUCCESS + "Successfully tabbed");
        send.println("done");

    }
}
