import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ConnectException;


public class Client{

  public static void main(String [ ] args) throws IOException{


    if(args.length != 2){
      System.out.println("To use type : java Client ip_adress_of_server port message");
      System.exit(1);
    }

    try{
      Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

      String s, messageInput;

      messageInput = input.readLine();
      System.out.println(messageInput);

      do{

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        s = bufferRead.readLine();

        output.println(s);

        messageInput = input.readLine();
        System.out.println(messageInput);

      }while(!s.contains("q"));

      messageInput = input.readLine();
      System.out.println(messageInput);

      input.close();
      output.close();
      socket.close();
    }
    catch(ConnectException e){
      System.out.println("Error : can't connect to the server");
      System.exit(1);
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
