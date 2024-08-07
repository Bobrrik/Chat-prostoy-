import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {

    ServerSocket serverSocket;
    ArrayList<Client> clients = new ArrayList<>();

    Chat() throws IOException {
        serverSocket = new ServerSocket(1234);

    }

    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void run() throws IOException {
        while (true) {
            System.out.println("ожидаем....");

            try {
                Socket socket = serverSocket.accept();
                System.out.println("юзер тут");

                clients.add(new Client(socket, this));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] arg) throws IOException {
        new Chat().run();
    }
}