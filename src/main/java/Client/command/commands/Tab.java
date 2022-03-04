package Client.command.commands;

import Client.command.ICommand;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.awt.*;
import java.util.ArrayList;

public class Tab implements ICommand {

    private final Robot robot = new Robot();

    public Tab() throws AWTException {
    }


    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        robot.keyPress(18);
        robot.keyPress(9);
        robot.keyRelease(18);
        robot.keyRelease(9);
        send.println(Prefixes.SUCCESS + "Successfully tabbed");
    }
}
