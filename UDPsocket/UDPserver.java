import java.io.*;
import java.net.*;

public class UDPserver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(53333); // establish socket and listen

        byte[] Buffer_in = new byte[256];

        DatagramPacket packet_in = new DatagramPacket(Buffer_in, Buffer_in.length);

        int count = 0;

        while (true) // wait for incoming data
        {
            socket.receive(packet_in);
            String IncomingData = new String(packet_in.getData(), 0, packet_in.getLength());

            if ("Hello UDP".equals(IncomingData)) { // prepare data to be sent
                count++;
                if (count >= 1000) {
                    String Response = "back at you UDP";

                    byte[] Buffer_out = Response.getBytes();
                    InetAddress Add = packet_in.getAddress();

                    int P = packet_in.getPort();

                    DatagramPacket packet_out = new DatagramPacket(Buffer_out, Buffer_out.length, Add, P);

                    socket.send(packet_out); // send data
                }
            }
        }
    }
}