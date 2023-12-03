package udp_echo;

import java.net.*;
import java.net.DatagramSocket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        // needs to give the server and port. If not, return
        if (args.length != 2) {
            // space between serverIP and serverPort IMPORTANT
            System.out.println("Syntax: EchoClient <serverIP> <serverPort>");
            return;
        }

        // create UDP socket
        DatagramSocket socket = new DatagramSocket();

        // args[0] is serverIP address, first element of Syntax array
        InetAddress serverIP = InetAddress.getByName(args[0]);
        // args[1] is serverPort, 2nd in Syntax array
        int serverPort = Integer.parseInt(args[1]);

        // read message from the user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a message: ");
        String message = keyboard.nextLine();

        // create packet to send to server
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, serverIP, serverPort);
        socket.send(sendPacket);

        // create UDP packet to receive echo from server
        DatagramPacket reply = new DatagramPacket(new byte[1024], 1024);
        // receive echoed message from server
        socket.receive(reply);

        // convert message to bytes
        byte[] serverMessage = reply.getData();

        // extract and print echoed message
        System.out.println(new String(serverMessage));
        // close the socket
        socket.close();
    }
}