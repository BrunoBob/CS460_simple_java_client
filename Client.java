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
import java.net.SocketException;


public class Client{

  public static void main(String [ ] args) throws IOException{

    //Check correct number of arguments
    if(args.length != 2){
      System.out.println("To use type : java Client ip_adress_of_server port message");
      System.exit(1);
    }

    try{
      //Create and connect the socket
      Socket socket = new Socket(args[0], Integer.parseInt(args[1]));

      //Class to read and write to the server
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

      String s, messageInput;

      System.out.println("Connected to the server");

      do{

        //Ask the user what he want to send to the server
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        s = bufferRead.readLine();

        //Send to the server
        output.println(s);

        //Listen for answer
        messageInput = input.readLine();
        System.out.println(messageInput);

      }while(!s.contains("q"));
      //End when end of client character

      System.out.println("Disconnected from the server");

      //Close the connection
      input.close();
      output.close();
      socket.close();
    }
    //Catch the possible errors
    catch(ConnectException e){
      System.out.println("Error : can't connect to the server");
      System.exit(1);
    }
    catch(SocketException e){
      System.out.println("Disconnected from the server");
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
