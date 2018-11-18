import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
 
public class ImageServer
{	private static Socket socket;
ImageServer(){FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
  
        try
        {	Socket sock = new Socket("192.168.1.103",8890);
				
			File myFile = new File ("D:/MachineLearningCloud.png");
			byte [] mybytearray  = new byte [(int)myFile.length()];
			  fis = new FileInputStream(myFile);
			  bis = new BufferedInputStream(fis);
			  bis.read(mybytearray,0,mybytearray.length);
			  os = sock.getOutputStream();
			  System.out.println("Sending " + "(" + mybytearray.length + " bytes)");
			  int fSize = (int) myFile.length();
				if (fSize < myFile.length())
				{
					System.out.println("File is too big'");
					throw new IOException("File is too big.");
				}

				// Send the file's size
				byte[] bSize = new byte[4];
				bSize[0] = (byte) ((fSize & 0xff000000) >> 24);
				bSize[1] = (byte) ((fSize & 0x00ff0000) >> 16);
				bSize[2] = (byte) ((fSize & 0x0000ff00) >> 8);
				bSize[3] = (byte) (fSize & 0x000000ff);
				// 4 bytes containing he file size
				os.write(bSize, 0, 4);

			  os.write(mybytearray,0,mybytearray.length);
			  os.flush();
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
	public static void main(String[] args)
    {
	FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
  
        try
        {	Socket sock = new Socket("192.168.1.102",8890);
				
			File myFile = new File ("D:/MachineLearningCloud.png");
			byte [] mybytearray  = new byte [(int)myFile.length()];
			  fis = new FileInputStream(myFile);
			  bis = new BufferedInputStream(fis);
			  bis.read(mybytearray,0,mybytearray.length);
			  os = sock.getOutputStream();
			  System.out.println("Sending " + "(" + mybytearray.length + " bytes)");
			  int fSize = (int) myFile.length();
				if (fSize < myFile.length())
				{
					System.out.println("File is too big'");
					throw new IOException("File is too big.");
				}

				// Send the file's size
				byte[] bSize = new byte[4];
				bSize[0] = (byte) ((fSize & 0xff000000) >> 24);
				bSize[1] = (byte) ((fSize & 0x00ff0000) >> 16);
				bSize[2] = (byte) ((fSize & 0x0000ff00) >> 8);
				bSize[3] = (byte) (fSize & 0x000000ff);
				// 4 bytes containing he file size
				os.write(bSize, 0, 4);

			  os.write(mybytearray,0,mybytearray.length);
			  os.flush();
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