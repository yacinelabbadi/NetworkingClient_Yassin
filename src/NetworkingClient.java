/* CLIENT */
import java.io.*;
import java.net.*;

public class NetworkingClient {
    public static void main(String[] args) {

        Socket client = null;

        // Default port number we are going to use
        int portNumber = 1234;
        if (args.length >= 1) {
            portNumber = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < 10; i++) {
            try {
                String msg = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portNumber);
                System.out.println("Client socket is created " + client);

                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                // Create bufferedReader for standard input
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter your name. Type bye to exit. ");

                // Read data from standard input device and write it
                // to the output stream of the client socket.
                msg = stdIn.readLine().trim();
                pw.println(msg);

                // Read data from the input stream of the client socket
                System.out.println("Message returned from server = " + br.readLine());

                pw.close();
                br.close();
                client.close();

                // Stop the operation
                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }
            } catch(IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}