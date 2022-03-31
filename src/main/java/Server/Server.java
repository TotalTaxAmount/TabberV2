package Server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;

public class Server {
    public void run(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        ArrayList<EchoThread> echoThreads = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server online @ port " + port);
        } catch (IOException e) {
            e.printStackTrace();

        }

        while (true) {
            try {
                assert serverSocket != null;
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            assert socket != null;
            System.out.println("\nNew Client connected @ ip " + socket.getInetAddress());
                 echoThreads.add(new EchoThread(socket));
                 int i = 0;
                 for (EchoThread echoThread : echoThreads) {
                     echoThread.setName("EchoThread-" + i);
                     echoThread.start();
                     i++;
                 }
                 //new EchoThread(socket).start();
                Set<Thread> threads = EchoThread.getAllStackTraces().keySet();
                System.out.println(threads);

        }
    }

    public static void main(String[] args) {
        Server srv = new Server();
        srv.run(4020);
    }
}