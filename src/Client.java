import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client implements Runnable {

    Scanner in;
    PrintStream out;
    Socket socket;
    Chat server;

    public Client(Socket socket, Chat server) {
        this.socket = socket;
        this.server = server;

        new Thread(this).start();
    }

    void receive(String message) {
        Date date = new Date();
        out.println("[" + date + "]: " + message);
    }

    public void run() {
        try {

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            in = new Scanner(is);
            out = new PrintStream(os);

            out.println("Welcome to chat!");

            String input = in.nextLine();

            while (!input.equals("bay")) {

                server.sendAll(input);
                input = in.nextLine();
            }
            System.out.println("пользователь вышел");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
