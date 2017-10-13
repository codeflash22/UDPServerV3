package udpserverv3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Asif
 */
public class UDPServerV3 {

    public static void main(String[] args) throws IOException, SocketException{
        Scanner input = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket(2245);
        boolean running = true;
        while(running){
            // Receiving from client
            byte[] bufRcv = new byte[1000];
            DatagramPacket dpRcv = new DatagramPacket(bufRcv, bufRcv.length);
            socket.receive(dpRcv);
            String receivedMsg = new String(dpRcv.getData(), 0, dpRcv.getLength());
            System.out.println("Client: " + receivedMsg);
            if(receivedMsg.equalsIgnoreCase("bye"))
                running = false;

            // Sending back to client
            String msg = input.nextLine();
            byte[] bufSend = msg.getBytes();
            DatagramPacket dpSend = new DatagramPacket(bufSend, bufSend.length, dpRcv.getAddress(), dpRcv.getPort());
            socket.send(dpSend);
            
        }
        
        
    }
    
}
