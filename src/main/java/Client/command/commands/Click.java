package Client.command.commands;

import Client.command.Command;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Click extends Command {
    public Click() {
        super("click", "Click on an object", "click [button] [delay]");
    }
    private Robot robot;
    private int times = 1, delay = 100;

    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void click(String button, int delay) throws InterruptedException {
        if (button.equalsIgnoreCase("l")) {
            robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
            robot.delay(delay);
            robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);

        }
        if (button.equalsIgnoreCase("r")) {
            robot.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
            robot.delay(delay);
            robot.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
        }
    }

    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        if(args.get(2) == null || (!args.get(2).equalsIgnoreCase("l")) && !args.get(2).equalsIgnoreCase("r")) {
             send.println("You need to specify what you want to do or you stupid and put the wrong shit. Example !click send r");
            return;
        }

        try {
            if (args.get(3) != null) {
                if(Integer.parseInt(args.get(3)) > 2000) {
                   send.println("You can't click that many times. Max 2000");
                    return;
                }
                times = Integer.parseInt(args.get(3));
            }
            if (args.get(4) != null) {
                if(Integer.parseInt(args.get(4)) > 500 && times >= 100) {
                    //extra = "Stop being a bitch boi and make a shorter delay or less amount of clicks";
                    send.println("Stop being a bitch boi and make a shorter delay or less amount of clicks");
                    return;
                }
                delay = Integer.parseInt(args.get(4));
            }
            for(int i = 0; i < times; i++) {
                click(args.get(2), delay);
                send.println("Sent button " + args.get(2).toUpperCase() + ", " + times + " times. With a delay of " + delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
