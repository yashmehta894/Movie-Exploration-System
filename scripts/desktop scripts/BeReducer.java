import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class BeReducer extends Reducer<MovieIdGenre, FloatWritable, Text, FloatWritable> {

    public void reduce(MovieIdGenre _key, Iterable<FloatWritable> values, Context context)
            throws IOException, InterruptedException {
        // process values
    	
    	float sum = 0;
    	int i = 0;
    	for(FloatWritable f : values){
    		sum = sum+f.get();
    		i++;
    	}
    	
    	context.write(new Text(_key.toString()),new FloatWritable(sum/i));
    	
    }
}