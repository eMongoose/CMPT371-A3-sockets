import java.io.*;
import java.net.*;

public class UDPclient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(); // establish socket

        String Message = "Hello UDP"; // prepare data to be sent

        // start timer
        long start = System.nanoTime();

        byte[] Buffer_out = Message.getBytes();

        DatagramPacket packet_out = new DatagramPacket(Buffer_out, Buffer_out.length,
                InetAddress.getByName("localhost"), 53333);

        // send data
        for (int i = 0; i < 1000; i++) {
            socket.send(packet_out);
        }

        // receive response
        byte[] Buffer_in = new byte[256];
        DatagramPacket packet_in = new DatagramPacket(Buffer_in, Buffer_in.length);
        socket.receive(packet_in);

        // end timer
        long end = System.nanoTime();
        double timer = (end - start) / 1000;

        String Response = new String(packet_in.getData(), 0, packet_in.getLength());

        System.out.println(Response);
        System.out.printf("Round Trip Time: " + timer + "ms");

        socket.close();
    }
}