import org.rosuda.REngine.Rserve.RConnection;

import org.rosuda.REngine.Rserve.RserveException;
import java.io.*;
import java.util.*;

public class CallScipt {


		public float returnRating(String movie_name){
			RConnection connection = null;
			float rating = 0;
			try {

				
				connection = new RConnection();
				String str = "movie_name="+"\""+movie_name+"\"";
				connection.eval(str);
				connection.eval("source('E:\\\\script.R')");
				ImageServer img = new ImageServer();//send the new collected image to server
				CalculateRatings cr = new CalculateRatings();
				rating = cr.sendRating();
				
				System.out.println(rating);
			} catch (RserveException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
			catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			finally{
					if(connection!=null)
					connection.close();
				
			}
			
			return rating; 
		
		}


    public static void main(String args[]) {
      //  returnRating("salman");
	  
	  
	  
	  CallScipt cs = new CallScipt();
	  System.out.println("Enter movie name");
	  try{
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  cs.returnRating(br.readLine());
	  }
	  catch(IOException e){
	  }
    } 
}