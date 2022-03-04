package Client.command;

import Client.command.commands.TestCommand;
import Client.command.commands.lucascmd;

import java.util.HashMap;

public class CMDFactory {
    private final HashMap<String, ICommand> commands;

    public CMDFactory() {
        this.commands = new HashMap<>();
        commands.put("test", new TestCommand());
        commands.put("bitches", new lucascmd());

    }

    public ICommand createCommand(String cmd) {
        if(commands.containsKey(cmd.toLowerCase())) {
            return commands.get(cmd.toLowerCase());
        }
        return new NoOP();
    }
}
