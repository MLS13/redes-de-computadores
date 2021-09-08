import java.io.*; // for IOException and Input/OutputStream
import java.net.*; // for Socket, ServerSocket
import java.util.Scanner;

public class Server {

  private static final int BUFSIZE = 32; // Size of receive buffer

  public static void main(String[] args) throws IOException {
    int servPort = 1500; //Porta qualquer apenas para não dar conflito

    //Criação do server socket
    ServerSocket servSock = new ServerSocket(servPort);

    for (;;) {
      System.out.println("Aguardando conexao...\n");
      Socket clntSock = servSock.accept();
      byte[] byteBuffer = new byte[BUFSIZE];
      System.out.println(
        "--------------------------------------------\nIP do cliente: " +
        clntSock.getInetAddress().getHostAddress() +
        "\nNa porta: " +
        clntSock.getPort()
      );
      InputStream in = clntSock.getInputStream();
      OutputStream out = clntSock.getOutputStream();
      in.read(byteBuffer);
      System.out.print("Mensagem do cliente: " + new String(byteBuffer));
      System.out.println("\nDigite sua mensagem:");
      Scanner scanner = new Scanner(System.in);
      byteBuffer = scanner.nextLine().getBytes();
      out.write(byteBuffer);
      clntSock.close();
      System.out.println(
        "Conexao encerrada\n--------------------------------------------\n"
      );
    }
  }
}
