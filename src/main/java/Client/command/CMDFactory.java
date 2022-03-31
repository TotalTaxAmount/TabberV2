package Client.command;

import Client.command.commands.*;
import Client.command.commands.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CMDFactory {
    private final ArrayList<Command> commands;

    public CMDFactory() throws AWTException {
        this.commands = new ArrayList<>();
        commands.add(new TestCommand());
        commands.add(new Admin());
        commands.add(new Tab());
        commands.add(new Info());
        commands.add(new Help());
        commands.add(new Token());
        commands.add(new Click());
        commands.add(new Window());
        commands.add(new AutoRun());


    }
    public ArrayList<Command> getCommands() {
        return commands;
    }
}
