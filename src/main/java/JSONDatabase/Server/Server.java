package Project4.Server;


import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 23456;
    private boolean run = true;


    public void setRun(boolean run) {
        this.run = run;
    }

    public Server() {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        try
                (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started!");
            Database database = new Database();

            do {
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                String msg = input.readUTF();
                System.out.println("Received: " + msg);
                Request request = new Gson().fromJson(msg, Request.class);
                if (request.getType().equals("exit")) {
                    setRun(false);
                    output.writeUTF("{\"response\":\"OK\"}");
                } else {
                    executor.submit(new EditRequestTask(socket, database, request, output));
                }
            } while (run);
            executor.shutdown();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}