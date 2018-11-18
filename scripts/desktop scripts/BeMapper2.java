import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;


public class BeMapper2 extends Mapper<LongWritable, Text,IntWritable , MovieIdRating> {

	public void map(LongWritable ikey, Text ivalue, Context context)
			throws IOException, InterruptedException {
		
		String line=ivalue.toString();
        //String[] tokens=StringUtils.split(line, ' ');
        StringTokenizer st = new StringTokenizer(line," ",false);
        if(st.countTokens() ==2)
        {	//System.out.println(tokens.toString());
        	
        	String token0 = st.nextToken();
        	String token1 = st.nextToken();
        	StringTokenizer st1 = new StringTokenizer(token0,",",false);
        	//String[] mg = StringUtils.split(token0, ',');
        	
        	int movieId = Integer.parseInt(st1.nextToken());
        	
        	StringTokenizer st2 = new StringTokenizer(st1.nextToken(),"|",false);
        	//String[] genreList = StringUtils.split(mg[1], '|');
        	
        	MovieIdRating mr = new MovieIdRating();
        	mr.set(movieId, Float.parseFloat(token1));
        	while(st2.hasMoreTokens()){
        		int genreId = Integer.parseInt(st2.nextToken());
        		context.write(new IntWritable(genreId), mr);
        	}
        }

	}
	/*1,4|6|2	4.0
	2,2|4	3.0
	3,5|2	4.0*/

}
