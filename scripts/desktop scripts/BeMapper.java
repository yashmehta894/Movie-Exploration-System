
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;


public class BeMapper extends Mapper<LongWritable, Text, MovieIdGenre, FloatWritable> {

    public void map(LongWritable ikey, Text ivalue, Context context)
            throws IOException, InterruptedException {
        String line=ivalue.toString();
        String[] tokens=StringUtils.split(line, ',');
        if(tokens.length ==4)
        {
            int movieId = Integer.parseInt(tokens[0]);
            int userId = Integer.parseInt(tokens[1]);
            float rating = Float.parseFloat(tokens[2]);
            MovieIdGenre movieidGenre = new MovieIdGenre();
            movieidGenre.set(movieId,tokens[3]);
            
            context.write(movieidGenre, new FloatWritable(rating));
           
        }
    }

}