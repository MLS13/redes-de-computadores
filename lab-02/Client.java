/*
Aluno: Miller Lopes da Silva
Matrícula: 2017018274
*/

import java.net.*; 
import java.io.*; 
import java.util.Scanner;

public class Client {
    private static final int TIMEOUT = 3000; 
    private static final int MAXTRIES = 5; 
    private static final int ECHOMAX = 255; 

    public static void main (String[] args) throws IOException {
        InetAddress serverAddress = InetAddress.getByName("localhost");
        byte[] bytesToSend = new byte[ECHOMAX];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a URL: ");
        String url = scanner.nextLine();
        bytesToSend = url.getBytes();
        int servPort = 1500;
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(TIMEOUT); // Maximum receive blocking time
        DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, servPort);
        DatagramPacket receivePacket = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
        int tries = 0;
        boolean receivedResponse = false;
        do {
            socket.send(sendPacket);
            try {
                socket.receive(receivePacket); 
                if (!receivePacket.getAddress().equals(serverAddress))
                    throw new IOException("Pacote recebido de uma fonte desconhecida.");
                receivedResponse = true;
            } catch (InterruptedIOException e) {
                tries += 1;
                System.out.println("Tempo esgotado, " + (MAXTRIES - tries) + " mais tentativas ...");
            }
        } while ((!receivedResponse) && (tries < MAXTRIES));
        if (receivedResponse)
            System.out.println("IP recebido: " + new String(receivePacket.getData()));
        else
            System.out.println("Sem resposta - encerrado.");
        socket.close();
    }
}