package Server;

import java.net.*;
import java.io.*;
import java.util.Set;

public class Server {
    public void run(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;

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
                System.out.println("New Client connected @ ip " + socket.getInetAddress());
                new EchoThread(socket).start();
                Set<Thread> threads = EchoThread.getAllStackTraces().keySet();
                System.out.println(threads);

        }
    }

    public static void main(String[] args) {
        Server srv = new Server();
        srv.run(4020);
    }
}