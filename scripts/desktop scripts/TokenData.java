import java.util.*;
import java.io.*;

import java.text.DateFormat;
class TokenData{
public static void main(String args[]) {
		
	String msgText = "You have received INR 15.00 from sandeepsk@axisbank.";
	StringTokenizer st = new StringTokenizer(msgText," ()/",false);
	String vpa="",amount="";
	while(st.hasMoreTokens()) {

                String str = st.nextToken();

                if (str.contains("@")) {
                    vpa = str;
                    if (vpa.endsWith(".")) {
                        vpa = vpa.substring(0, vpa.length() - 1);
                    }
                }
                if (str.contains("INR") || str.contains("Rs")) {
                    amount = st.nextToken();
                }
    }
	System.out.println(vpa+" "+amount+" "+DateFormat.getDateInstance().format(new Date()));
	
	

	
	
	
}


}
