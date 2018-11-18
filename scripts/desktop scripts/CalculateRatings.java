
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.*;
import java.util.*;

class CalculateRatings{


static ArrayList<String> arr = new ArrayList<>();


public float sendRating() throws IOException, ClassNotFoundException{
	MaxentTagger tagger = new MaxentTagger("taggers/left3words-wsj-0-18.tagger");
	String pathToSWN = "D:/SentiWordNet_3.0.0_20130122.txt";
    SentiWordNetDemoCode sentiwordnet = new SentiWordNetDemoCode(pathToSWN);
	
	int same = 0;
	int totalRecords = 0;
    
    CSVReader reader = new CSVReader(new FileReader("D:\\list.csv"), ','); 
    CSVWriter writer = new CSVWriter(new FileWriter("D:\\newlist.csv",false));
	
	writer.writeNext(new String[]{"Tweet","postag value","date value","favourited value","P(yes)","P(no)","Yes/No"});
	
	float num_yes = 0, num_no = 0;
    
    String rowData[] = null;
    while((rowData = reader.readNext())!=null){
		String sample = rowData[1];
		if(!arr.contains(sample)){
			arr.add(sample);
			totalRecords++;
		
		
			String label ="";
			
			StringTokenizer st = new StringTokenizer(sample," !",false);
			String finalString = "";

			//System.out.println(s);
			while(st.hasMoreTokens()){
				String str = st.nextToken();
				if((str.charAt(0)+"").equals("#") || (str.charAt(0)+"").equals("&")|| (str.charAt(0)+"").equals("@") || (str.charAt(0)+"").equals("<") || (str.startsWith("http"))){
					//finalString = finalString + " ";
				}
				else{
					finalString = finalString + " "+str ;
				}
			}
			String tagged = tagger.tagString(finalString);
		   
		   // System.out.println(finalString);
			st = new StringTokenizer(tagged," ",false);
			double postag = 0;
			
			//int date = 0;
			//int fav = 0;
			while(st.hasMoreTokens()){
				String str = st.nextToken();
				StringTokenizer st1 = new StringTokenizer(str,"/",false);
				String word = st1.nextToken();
				String tag = st1.nextToken();
				double score = 0;
				if(tag.startsWith("JJ")){ 	//this means its an adjective
					 score = sentiwordnet.extract(word, "a");
				}
				postag = postag + score;	//unigram value
			}
			//ProbCalc probCalc = new ProbCalc();
			//P_yes(postag>0, date = 3 , fav = 0) = P(posTag>0|yes)* P(date=3|yes) * P(fav=0 | yes) * P(yes);
			//P_no(postag>0, date = 3 , fav = 0) = P(posTag>0|no)* P(date=3|no) * P(fav=0 | no) * P(no);
			
			float date_value_yes =0,date_value_no=0, fav_value_yes=0, fav_value_no=0, postag_value_yes=0, postag_value_no=0;
			
			int[] items = new int[]{1,3,5};
				Random rand = new Random();
				int[] favitems = new int[]{0,1};
				Random rand1 = new Random();
				int date = items[rand.nextInt(items.length)];
					int fav = favitems[rand1.nextInt(favitems.length)];
			
			if(date==1){
				date_value_yes = 259;
				date_value_no = 259;
			}
			if(date==3){
				date_value_yes = 240;
				date_value_no = 247;
			}
			if(date==5){
				date_value_yes = 258;
				date_value_no = 258;
			}
			if(fav==0){
				fav_value_yes = 363;
				fav_value_no = 385;
			}
			if(fav==1){
				fav_value_yes = 379;
				fav_value_no = 379;
			}
			if(postag>=0){
				postag_value_yes = 227;
				postag_value_no = 132;
			}
			if(postag<0){
				postag_value_yes = 59;
				postag_value_no = 92;
			}
			float getYes = 742;
			float getNo = 764;
			float total = 1506;
			float P_yes = date_value_yes / getYes * fav_value_yes / getYes * postag_value_yes / getYes * getYes / total;
			float P_no = date_value_no / getNo * fav_value_no / getNo * postag_value_no / getNo * getNo / total;
			
			
			
			
			if(P_yes>=P_no){
				label = "yes";
				num_yes++;
			}
			else{
				num_no++;
				label = "no";
			}
			
			String[] finalWrite = {sample,postag+"",date+"",fav+"",P_yes+"",P_no+"",label};
			writer.writeNext(finalWrite);
		
		
		}
	
    
	}
	
	
    reader.close();
    writer.close();
	
	float finalrating = num_yes/(num_yes+num_no)*10; 
	
    return finalrating;
}


CalculateRatings() {


	
}


public static void main(String [] args) throws IOException, ClassNotFoundException {
	
	CalculateRatings cr = new CalculateRatings();
	cr.sendRating();
	
}

}