import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConcertDevice {
  public static void main(String[] args) throws IOException {
    ServerSocket listener = new ServerSocket(8888);
    try {
      while (true) {
        Socket socket = listener.accept();
        try {

          BufferedReader input =
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
          String answer = input.readLine();


          String noTpe = answer.substring(0,2);
          String amount = answer.substring(2,10);
          char answerFlag = answer.charAt(10);
          char paymentMode = answer.charAt(11);
          char transactionType = answer.charAt(12);
          String currency = answer.substring(13,16);

          System.out.println("-----------");
          System.out.println("Raw: " + answer);
          System.out.println("num TPE: " + noTpe);
          System.out.println("amount: " + amount);
          System.out.println("answer flag: " + answerFlag);
          System.out.println("payment mode: " + paymentMode);
          System.out.println("transaction type: " + transactionType);
          System.out.println("currency: " + currency);

          PrintWriter out =
            new PrintWriter(socket.getOutputStream(), true);

          String responseAK = "0";
          String responseCanceled = "1";
          String responseNAK = "7";

          out.println(noTpe + responseAK + amount + paymentMode);


        } finally {
          socket.close();
        }
      }
    }
    finally {
      listener.close();
    }
  }
}
