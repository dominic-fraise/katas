package org.example;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.printf("Hello and welcome!");

        ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(8080);
        StringBuilder body = new StringBuilder("<html><body>Hello world</body></html>");
        StringBuilder body2 = new StringBuilder("<html><body>Test</body></html>");


        while(true) {
            Socket accept = serverSocket.accept();

            InputStream inputStream = accept.getInputStream();
            String fullRequest = new String(inputStream.readAllBytes());
            String firstHeader = fullRequest.substring(0, fullRequest.indexOf("\n"));
            String[] pathParts = firstHeader.split(" ");
            String path = pathParts[1];

            System.out.println(path);
            Runnable t = () -> {
//                if()
                try {
                    createHttpResponse(accept, body);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            t.run();

        }



    }

    private static void createHttpResponse(Socket accept, StringBuilder body) throws IOException {
        PrintStream ps = new PrintStream(accept.getOutputStream());
        ps.println("HTTP/1.1 200 OK");
        ps.println("Date: Mon, 27 Jul 2009 12:28:53 GMT");
        ps.println("Server: Java");
        ps.println("Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT");
        ps.println("Content-Length: " + body.length());
        ps.println("Content-Type: text/html");
        ps.println("Connection: Closed");
        ps.println();
        ps.println(body);
    }
}