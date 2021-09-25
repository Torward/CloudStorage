import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerIO {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8700);
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.execute(()->{
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ) {
                    String str = reader.readLine();
                    System.out.println(str);
                    writer.write("Hi!");

                } catch (IOException e) {
                    System.err.println("Smth went wrong!");
                }
            });

        }

    }
}
