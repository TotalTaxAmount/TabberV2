package Server;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;

public class Server {
    private ArrayList<EchoThread> echoThreads;
    private EchoThread currentThread;

    public void run(int port) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        echoThreads = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server online @ port " + port);
        } catch (IOException e) {
            e.printStackTrace();

        }

        while (true) {
            System.out.println("Select a thread to run: ");
            int a = 1;
            for (EchoThread echoThread : echoThreads) {
                System.out.println("[" + a + "] " + echoThread.getName());
            }

            update();

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
                 i++;
             }


                 //new EchoThread(socket).start();

        }
    }
    public EchoThread select(String echoThread) {
        try {
            Integer.parseInt(echoThread);
        } catch (NumberFormatException e){
            System.out.println("\"" + echoThread + "\" is not a number");
            return null;
        }

        echoThread = "EchoThread-" + (Integer.parseInt(echoThread) - 1);

        for (EchoThread echoThread1 : echoThreads) {
            if (echoThread1.getName().equals(echoThread)) {
                echoThread1.start();
                return echoThread1;

            } else if (echoThread1.isAlive()) {
                echoThread1.interrupt();
            }
        }
        return null;
    }

    public void update() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        if (this.select(input) == null) {
            System.out.println("Thread not found");
            return;
        } else {
            currentThread = this.select(input);
            currentThread.closed = false;
            System.out.println("Thread selected: " + currentThread.getName());
        }
        if (currentThread.closed) {
            System.out.println("Thread Closed");
            currentThread = null;
        }
    }

    public static void main(String[] args) throws IOException {
        Server srv = new Server();
        srv.run(4020);

    }
}