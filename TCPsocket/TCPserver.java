import java.io.*;
import java.net.*;

public class TCPserver {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(53333); // Create a server socket

        while (true) {

            Socket client = server.accept(); // wait for incoming connection requests

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            // read token
            String request = in.readLine();

            int count = 0; // set a counter
            String line;
            // if the client says hello, return a message
            while (count < 1000 && (line = in.readLine()) != null) {
                if ("hello TCP".equals(line)) {
                    out.println("back at you TCP");
                    count++;
                }
            }
            out.println("back at you TCP");

            out.close();
            in.close();
            client.close();
        }
    }
}