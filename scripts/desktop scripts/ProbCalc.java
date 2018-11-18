
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import com.opencsv.CSVReader;
public class ProbCalc {
	
	static int no_yes = 0, no_no = 0, total_no = 0, no_posGrtZero = 0, no_posLessZero = 0,yes_date1,no_date1,yes_fav1,no_fav1,
			yes_date3,no_date3, yes_date5, no_date5, yes_fav0, no_fav0, yes_posGrtZero=0,yes_posLessZero=0 ;
	ProbCalc(){
	}
	
	
	public static void main(String[] args) throws IOException{
		Initializer();
		
		System.out.println(getYesDate1()+" "+getNoDate1()+" "+getYesDate3()+" "+getNoDate3()+" "+getYesDate5()+" "+getNoDate5());
		System.out.println(getYesFav1()+" " + getNoFav1()+" "+getYesFav0()+" "+getNoFav0());
		System.out.println(getYesPostGrtZero()+" "+getYesPostLessZero()+" "+getNoPostGrtZero()+" "+getNoPostLessZero());
		System.out.println(getYes()+" "+getNo()+" "+getTotal());
	}
	
	static void Initializer() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("D:\\PosTaggedTrainingData.csv"), ','); 
		String rowData[] = null;
		int[] items = new int[]{1,3,5};
	    Random rand = new Random();
	    int[] favitems = new int[]{0,1};
	    Random rand1 = new Random();
	    rowData = reader.readNext();
		rowData = reader.readNext();
	    while((rowData = reader.readNext())!=null){
			//System.out.println(rowData[0]+ " " + rowData[1]);
			String label = rowData[1];
			float postag = Float.parseFloat(rowData[2]);//findPosTagValue(rowData[0]);
			int date = items[rand.nextInt(items.length)];
			int fav = favitems[rand1.nextInt(favitems.length)];

			if(label.equals("yes")){
				no_yes++;
				if(postag>0){
					yes_posGrtZero++;
				}
				if(postag<0){
					yes_posLessZero++;
				}
				if(date==1){
					yes_date1++;
				}
				if(date==3){
					yes_date3++;
				}
				if(date==5){
					yes_date5++;
				}
				if(fav==0){
					yes_fav0++;
				}
				if(fav==1){
					yes_fav1++;
				}
			}
			else{
				no_no++;
				if(postag>0){
					no_posGrtZero++;
				}
				if(postag<0){
					no_posLessZero++;
				}
				if(date==1){
					no_date1++;
				}
				if(date==3){
					no_date3++;
				}
				if(date==5){
					no_date5++;
				}
				if(fav==0){
					no_fav0++;
				}
				if(fav==1){
					no_fav1++;
				}
			}
			total_no++;
		}
		reader.close();
	}
	static float getYesDate1(){
		return yes_date1;
	}
	static float getNoDate1(){
		return no_date1;
	}
	static float getYesDate3(){
		return yes_date3;
	}
	static float getNoDate3(){
		return no_date3;
	}
	static float getYesDate5(){
		return no_date5;
	}
	static float getNoDate5(){
		return no_date5;
	}
	static float getYesFav1(){
		return yes_fav1;
	}
	static float getNoFav1(){
		return no_fav1;
	}
	static float getYesFav0(){
		return yes_fav0;
	}
	static float getNoFav0(){
		return no_fav0;
	}
	static int getYes(){
		return no_yes++;
	}
	static int getNo(){
		return no_no++;
	}
	static int getTotal(){
		return total_no;
	}
	static float getYesPostGrtZero(){
		return yes_posGrtZero;
	}
	static float getYesPostLessZero(){
		return yes_posLessZero;
	}
	static float getNoPostGrtZero(){
		return no_posGrtZero;
	}
	static float getNoPostLessZero(){
		return no_posLessZero;
	}
}