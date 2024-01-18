import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Server extends JFrame {
    private JTextArea serverTextArea;
    private JTextField messageTextField;
    private JButton sendButton;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server() {
        setTitle("Chat Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        serverTextArea = new JTextArea();
        serverTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(serverTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        messageTextField = new JTextField(20);
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        bottomPanel.add(messageTextField);
        bottomPanel.add(sendButton);

        add(bottomPanel, BorderLayout.SOUTH);

        initializeServer();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeServer();
            }
        });
    }

    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(2102);
            serverTextArea.append("Server is running...\n");

            clientSocket = serverSocket.accept();
            serverTextArea.append("Connection is successfully established...\n");

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    listenForMessages();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForMessages() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                serverTextArea.append("Client Says: " + message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = messageTextField.getText();
        writer.println(message);
        serverTextArea.append("You: " + message + "\n");
        messageTextField.setText("");
    }

    private void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server().setVisible(true);
            }
        });
    }
}
