import java.lang.*;
import java.net.*;
import java.io.*;

class Server
 {
    public static void main(String[] args) throws Exception {
        System.out.println("Server Application is running....");
        String s1, s2;

        ServerSocket ss = new ServerSocket(1100);
        Socket s = ss.accept();

        System.out.println("Connection successful...");
        PrintStream ps = new PrintStream(s.getOutputStream());

        BufferedReader brk = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader((new InputStreamReader(s.getInputStream())));
        while ((s1 = br.readLine()) != null) {
            System.out.println("Client says:" + s1);
            System.out.println("Enter message for client");

            s2 = brk.readLine();
            ps.println(s2);
        }
        s.close();
        ps.close();
        ss.close();
        br.close();
        brk.close();

    }
}