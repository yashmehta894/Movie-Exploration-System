import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class BeReducer2 extends Reducer<IntWritable , MovieIdRating, IntWritable, Text> {

	public void reduce(IntWritable _key, Iterable<MovieIdRating> values, Context context)
			throws IOException, InterruptedException {
		// process values
		//String str = "";
		for(MovieIdRating val : values){
			//str = str + "<"+val.getMovieID()+","+val.getRating()+">";
			String str = "<"+val.getMovieID()+","+val.getRating()+">";
			Text t = new Text();
			t.set(str);
			context.write(_key, t);
		}
		//Text t = new Text();
		//t.set(str);
		//context.write(_key, t);
	}

}
