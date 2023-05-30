package be;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class client
{
    private static Socket socket;
    
    public static void main(final String[] args) {
        try {
            final String host = "localhost";
            final int port = 25000;
            final InetAddress address = InetAddress.getByName(host);
            client.socket = new Socket(address, port);
            final OutputStream os = client.socket.getOutputStream();
            final OutputStreamWriter osw = new OutputStreamWriter(os);
            final BufferedWriter bw = new BufferedWriter(osw);
            final String number = "2";
            final String sendMessage = String.valueOf(number) + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : " + sendMessage);
            final InputStream is = client.socket.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            final BufferedReader br = new BufferedReader(isr);
            final String message = br.readLine();
            System.out.println("message received from the server : " + message);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            try {
                client.socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        finally {
            try {
                client.socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            client.socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}