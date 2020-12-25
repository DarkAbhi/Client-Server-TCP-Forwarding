package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket(SERVER_IP, Integer.parseInt(args[0]));
            TCPServerConnection serverConnection = new TCPServerConnection(socket);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(serverConnection).start();
            while (true) {
                System.out.print("> ");
                String command = keyboard.readLine();
                if (command.equals("quit")) break;
                out.println(command);
            }
            socket.close();
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException a) {
            System.err.println("Enter the port number of server i.e 9090");
        }
    }
}
