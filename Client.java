import java.lang.*;
import java.net.*;
import java.io.*;

class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Client Application is running.....");
        String s1, s2;

        Socket s = new Socket("localhost", 1100);
        
        PrintStream ps = new PrintStream(s.getOutputStream());
        BufferedReader br = new BufferedReader((new InputStreamReader(s.getInputStream())));
        BufferedReader brk = new BufferedReader(new InputStreamReader(System.in));
        while (!(s1 = brk.readLine()).equals("exit")) {

            ps.println(s1);
            s2 = br.readLine();

            System.out.println("Server Says :" + s1);
            
            System.out.println("Enter the message for server");
        }
        s.close();
        ps.close();
        br.close();
        brk.close();

    }
}