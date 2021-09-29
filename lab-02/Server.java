/*
Aluno: Miller Lopes da Silva
Matrícula: 2017018274
*/

import java.net.*;
import java.io.*; 
import java.util.*;

public class Server {
    private static final int ECHOMAX = 255; 

    public static void main (String[] args) throws IOException {
        Map<String,String> tabela = new HashMap<String,String>();
        //Utilizei o comando ping no cmd do windows para encontrar os ips
        tabela.put("www.unifei.edu.br","200.131.128.48");
        tabela.put("www.facebook.com","157.240.12.35");
        tabela.put("www.instagram.com","31.13.71.174");
        tabela.put("www.ge.globo.com","186.192.81.25");
        tabela.put("www.twitch.tv","151.101.66.167");
        tabela.put("www.twitter.com","104.244.42.1");
        tabela.put("www.youtube.com","142.250.219.14");
        tabela.put("www.google.com","142.250.218.238");
        tabela.put("www.uol.com.br","200.147.35.149");
        tabela.put("www.discord.com","162.159.137.232");

        int servPort = 1500; //Porta qualquer apenas para não dar conflito
        DatagramSocket socket = new DatagramSocket(servPort);
        DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
        for (;;) {
            socket.receive(packet); 
            System.out.println(
                "--------------------------------------------\n" + 
                "IP do cliente: " +
                packet.getAddress().getHostAddress() +
                "\nNa porta: " +
                packet.getPort()
            );
            String url = new String(packet.getData(),0, packet.getLength());
            System.out.println("URL: " + url);
            String ip = tabela.get(url);  
            if(ip==null)              
                ip = "nao encontrado";                
            System.out.println("IP: " + ip);
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            DatagramPacket packetSend = new DatagramPacket(ip.getBytes(), ip.getBytes().length, address, port);
            socket.send(packetSend); 
            packet.setLength(ECHOMAX); 
        }
    }
}