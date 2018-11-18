import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.io.DataOutputStream;
import java.io.OutputStreamWriter;

import java.net.ServerSocket;
import java.net.Socket;
 
public class Server
{
 
    private static Socket socket;
 
    public static void main(String[] args)
    {
        try
        {
 
            int port = 8888;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 8888");
 
            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String movie_name = br.readLine();
                System.out.println("Message received from client is "+movie_name);
 
                //Multiplying the number by 2 and forming the return message
                String returnMessage;
                CallScipt  cs = new CallScipt();
				float ratings = cs.returnRating(movie_name);
				returnMessage =  ratings+"";
				//returnMessage =  "9.08";
				
				Socket socket1 = new Socket("192.168.1.103",8880);
				
				
				//DataOutputStream dout = socket1.getOutputStream();
				//dout.write(returnMessage);
				//dout.flush();
 
                //Sending the response back to the client.
                OutputStream os = socket1.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client is "+returnMessage);
                bw.flush();
				bw.close();
				osw.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}