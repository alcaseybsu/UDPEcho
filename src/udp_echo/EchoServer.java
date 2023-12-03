package udp_echo;

import java.net.*;
import java.util.Arrays;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        // check if correct number of arguments is provided
        if (args.length != 1) {
            System.out.println("Syntax: EchoServer <serverPort>");
            return;
        }

        // parse server port number
        int serverPort = Integer.parseInt(args[0]);

        // create UDP socket for server
        try (DatagramSocket serverSocket = new DatagramSocket(serverPort)) {

            System.out.println("EchoServer is running on port " + serverPort);

            while (true) {
                // create UDP packet to receive data from client
                DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);

                // receive data from client
                serverSocket.receive(receivePacket);

                // extract message from client
                byte[] clientMessage = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());

                // display the received message
                System.out.println("Received from client: " + new String(clientMessage));

                // create UDP packet to send echoed message back to client
                DatagramPacket sendPacket = new DatagramPacket(clientMessage, clientMessage.length,
                        receivePacket.getAddress(), receivePacket.getPort());

                // send echoed message back to client
                serverSocket.send(sendPacket);
            }
        }
    }
}
