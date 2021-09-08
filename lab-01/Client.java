import java.io.*; // for IOException and Input/OutputStream
import java.net.*; // for Socket
import java.util.Scanner;

public class Client {

  public static void main(String[] args) throws IOException {
    String server = "localhost";
    int servPort = 1500;
    System.out.println("Iniciando chat...");
    System.out.println("Para sair do canal, digite '/sair'\n");
    for(;;) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite sua mensagem: ");
      String mensagem = scanner.nextLine();
      if(mensagem.equals("/sair")) break;
      byte[] byteBuffer = new byte[32];
      byteBuffer = mensagem.getBytes();
      Socket socket = new Socket(server, servPort);
      System.out.println("Enviando mensagem...");
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();
      out.write(byteBuffer);
      byteBuffer = new byte[32];
      System.out.println("Aguardando resposta...");
      in.read(byteBuffer);
      System.out.println("Mensagem recebida: " + new String(byteBuffer) + "\n");
      socket.close();
    }
    System.out.println("Chat encerrado");
  }
}
