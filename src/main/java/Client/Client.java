package Client;

import Client.command.CMDFactory;
import Client.command.ICommand;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private Socket socket;
    private final CMDFactory cmdfactory = new CMDFactory();
    private static final Pattern pattern = Pattern.compile("(\\w+)\\s?([a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+)?\\s?([a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+)?\\s?([a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+)?\\s?([a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+)?\\s?([a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+)?");

    public Client() throws AWTException {
    }


    public void run() throws InterruptedException {
        int serverPort = 4020;
        while (true) {
            try {
                InetAddress host = InetAddress.getByName("localhost");
                while (true) {
                    try {
                        socket = new Socket(host, serverPort);
                        break;
                    } catch (Exception ignored) {
                        System.out.println("Error connecting to server retrying in 5 seconds...");
                        Thread.sleep(5000);
                    }
                }
                System.out.println("Connected to server");
                PrintWriter send = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String cmd = "";

                ICommand cmdObject;
                ArrayList<String> args = new ArrayList<>();
                Matcher matcher;

                while (!cmd.equalsIgnoreCase("exit")) {
                    args.clear();
                    cmd = receive.readLine();
                    matcher = pattern.matcher(cmd);
                    if (matcher.matches()) {
                        for (int j = 0; j <= matcher.groupCount(); j++) {
                            args.add(matcher.group(j));
                        }
                    }
                    if (args.size() > 0) {
                        cmdObject = cmdfactory.createCommand(args.get(1));
                        cmdObject.run(args, receive, send);
                    }
                }
                    send.close();
                    receive.close();
                    socket.close();
                    break;

            } catch(IOException | InterruptedException ignored){
                System.out.println("Server unexpectedly closed connection trying to reconnect in 10 seconds...");
                Thread.sleep(10000);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, AWTException {
        Client client = new Client();
        client.run();
    }
}