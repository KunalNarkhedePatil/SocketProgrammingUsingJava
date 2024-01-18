import java.lang.*;
import java.net.*;
import java.io.*;

class Server
 {
    public static void main(String[] args) throws Exception {
        System.out.println("Server Application is running....");
        String str1, str2;

        ServerSocket ss = new ServerSocket(1100);
        Socket s = ss.accept();

        System.out.println("Connection successful...");
        PrintStream ps = new PrintStream(s.getOutputStream());

        BufferedReader br1 = new BufferedReader((new InputStreamReader(s.getInputStream())));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        while ((str1 = br1.readLine()) != null) {
            System.out.println("Client says:" + str1);
            System.out.println("Enter message for client");

            str2 = br2.readLine();
            ps.println(str2);
        }
        s.close();
        ps.close();
        ss.close();
        br1.close();
        br2.close();

    }
}