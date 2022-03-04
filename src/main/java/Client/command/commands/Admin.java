package Client.command.commands;

import Client.command.ICommand;
import Client.utils.ConsoleColor;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import static java.lang.System.setErr;
import static java.util.prefs.Preferences.systemRoot;

public class Admin implements ICommand {
    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        if(isRunningAsAdministrator()) {
            send.println(Prefixes.SUCCESS + "TabberV2 is admin (good)");
        } else {
            send.println(Prefixes.ERROR + "TabberV2 is not admin");
        }
    }

    private static boolean isRunningAsAdministrator()
    {
        Preferences preferences = systemRoot();

        synchronized (System.err)
        {
            setErr(new PrintStream(new OutputStream()
            {
                @Override
                public void write(int b)
                {
                }
            }));

            try
            {
                preferences.put("foo", "bar"); // SecurityException on Windows
                preferences.remove("foo");
                preferences.flush(); // BackingStoreException on Linux
                return true;
            } catch (Exception e)
            {
                return false;
            } finally
            {
                //setErr(System.err);
            }
        }
    }

}
