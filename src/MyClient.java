import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    Socket socket;
    private String address;
    private int port;

//Nel main creo l'oggetto e poi faccio start
    public static void main (String args[]){
        if (args.length!=2) {
            System.out.println("Usage: java MyClient <address> <port>");
            return;
        }
        MyClient client = new MyClient(args[0], Integer.parseInt(args[1]));
        //args0 e args1 perchè prima ho l'indirizzo e poi la porta
        client.start();

    }

    public MyClient(String address, int port){
        this.address = address;
        this.port = port;
    }

    public void start(){
        System.out.println("Starting client connection to"+address+":"+port);

        try {
            socket = new Socket(address,port);
//Non c'è dubbio che address e port siano stati settati perchè il costruttore di
//MyClient lo abbiamo fatto obbligatorio che vuole address e port
            System.out.println("Started client connection to"+address+":"+port);
            //to server
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            //from server
            Scanner scanner = new Scanner(socket.getInputStream());
            //from user
            Scanner user_scanner = new Scanner(System.in);
            boolean go = true;

            while(go){

                System.out.println("Insert a string to send");
                String messagge_to_send = user_scanner.nextLine();
                System.out.println("Sending "+messagge_to_send);
                pw.println();
                pw.flush();

                String received_message = scanner.nextLine();
                System.out.println("Received: "+received_message);

                if(messagge_to_send.equals("QUIT")){
                    System.out.println("Closing connection to"+socket.getRemoteSocketAddress());
                    socket.close();
                    go = false;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
