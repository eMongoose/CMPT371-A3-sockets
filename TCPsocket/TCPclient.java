import java.io.*;
import java.net.*;

public class TCPclient {
    public static void main(String[] args) throws IOException {

        Socket MySocket = new Socket("localhost", 53333); // establish connection

        PrintWriter out = new PrintWriter(MySocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(MySocket.getInputStream()));

        // start timer
        long start = System.nanoTime();

        // send a greeting
        for (int i = 0; i < 1000; i++) {
            out.println("hello TCP");
        }

        // server's response
        String serverResponse = in.readLine();

        // end timer
        long end = System.nanoTime();
        double timer = (end - start) / 1000;

        System.out.println(serverResponse);
        System.out.printf("Round Trip Time: " + timer + "ms");

        out.close();
        in.close();
        MySocket.close();
    }
}