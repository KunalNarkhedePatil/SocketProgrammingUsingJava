import java.lang.*;
import java.net.*;
import java.io.*;

class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Client Application is running.....");
        String str1, str2;

        Socket s = new Socket("localhost", 1100);
        
        PrintStream ps = new PrintStream(s.getOutputStream());
        BufferedReader br1 = new BufferedReader((new InputStreamReader(s.getInputStream())));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        while (!(str1 = br2.readLine()).equals("exit")) {

            ps.println(str1);
            str2 = br1.readLine();

            System.out.println("Server Says :" + str2);
            
            System.out.println("Enter the message for server");
        }
        s.close();
        ps.close();
        br1.close();
        br2.close();

    }
}