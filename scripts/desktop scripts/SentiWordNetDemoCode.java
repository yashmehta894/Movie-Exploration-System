

import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.IOException;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Random;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class SentiWordNetDemoCode {

private Map<String, Double> dictionary;

public static void main(String[] args) throws IOException, ClassNotFoundException{
new SentiWordNetDemoCode();
}


public SentiWordNetDemoCode(String pathToSWN) throws IOException, ClassNotFoundException {
	// This is our main dictionary representation
	dictionary = new HashMap<String, Double>();

	// From String to list of doubles.
	HashMap<String, HashMap<Integer, Double>> tempDictionary = new HashMap<String, HashMap<Integer, Double>>();

	BufferedReader csv = null;
	try {
		csv = new BufferedReader(new FileReader(pathToSWN));
		int lineNumber = 0;

		String line;
		while ((line = csv.readLine()) != null) {
			lineNumber++;

			// If it's a comment, skip this line.
			if (!line.trim().startsWith("#")) {
				// We use tab separation
				String[] data = line.split("\t");
				String wordTypeMarker = data[0];

				// Example line:
				// POS ID PosS NegS SynsetTerm#sensenumber Desc
				// a 00009618 0.5 0.25 spartan#4 austere#3 ascetical#2
				// ascetic#2 practicing great self-denial;...etc

				// Is it a valid line? Otherwise, through exception.
				if (data.length != 6) {
					throw new IllegalArgumentException(
							"Incorrect tabulation format in file, line: "
									+ lineNumber);
				}

				// Calculate synset score as score = PosS - NegS
				Double synsetScore = Double.parseDouble(data[2])
						- Double.parseDouble(data[3]);

				// Get all Synset terms
				String[] synTermsSplit = data[4].split(" ");

				// Go through all terms of current synset.
				for (String synTermSplit : synTermsSplit) {
					// Get synterm and synterm rank
					String[] synTermAndRank = synTermSplit.split("#");
					String synTerm = synTermAndRank[0] + "#"
							+ wordTypeMarker;

					int synTermRank = Integer.parseInt(synTermAndRank[1]);
					// What we get here is a map of the type:
					// term -> {score of synset#1, score of synset#2...}

					// Add map to term if it doesn't have one
					if (!tempDictionary.containsKey(synTerm)) {
						tempDictionary.put(synTerm,
								new HashMap<Integer, Double>());
					}

					// Add synset link to synterm
					tempDictionary.get(synTerm).put(synTermRank,
							synsetScore);
				}
			}
		}

		// Go through all the terms.
		for (Map.Entry<String, HashMap<Integer, Double>> entry : tempDictionary
				.entrySet()) {
			String word = entry.getKey();
			Map<Integer, Double> synSetScoreMap = entry.getValue();

			// Calculate weighted average. Weigh the synsets according to
			// their rank.
			// Score= 1/2*first + 1/3*second + 1/4*third ..... etc.
			// Sum = 1/1 + 1/2 + 1/3 ...
			double score = 0.0;
			double sum = 0.0;
			for (Map.Entry<Integer, Double> setScore : synSetScoreMap
					.entrySet()) {
				score += setScore.getValue() / (double) setScore.getKey();
				sum += 1.0 / (double) setScore.getKey();
			}
			score /= sum;

			dictionary.put(word, score);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (csv != null) {
			csv.close();
		}
	}
}

public double extract(String word, String pos) {
	if(dictionary.containsKey(word+"#"+pos)){
		return dictionary.get(word + "#" + pos);
	}
	else
		return 0;
}
public SentiWordNetDemoCode() throws IOException, ClassNotFoundException{
	MaxentTagger tagger = new MaxentTagger("taggers/left3words-wsj-0-18.tagger");
	String pathToSWN = "D:/SentiWordNet_3.0.0_20130122.txt";
    SentiWordNetDemoCode sentiwordnet = new SentiWordNetDemoCode(pathToSWN);
    CSVReader reader = new CSVReader(new FileReader("D:\\trainingdata.csv"), ','); 
	CSVWriter writer = new CSVWriter(new FileWriter("D:\\PosTaggedTrainingData.csv",true));
	
	
    String rowData[] = reader.readNext();
    
    while((rowData = reader.readNext())!=null){
    String sample = rowData[0];
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
    double finalScore = 0;
    
    while(st.hasMoreTokens()){
    	String str = st.nextToken();
    	StringTokenizer st1 = new StringTokenizer(str,"/",false);
    	String word = st1.nextToken();
    	String tag = st1.nextToken();
    	double score = 0;
    	if(tag.startsWith("JJ")){ 	//this means its an adjective
    		 score = sentiwordnet.extract(word, "a");
    	}
    	finalScore = finalScore + score;	//unigram value
		
    }
	String[] arr = new String[3];
	arr[0] = sample;
	arr[1] = rowData[1];
	arr[2] = finalScore+"";
	writer.writeNext(arr);
	System.out.println(Arrays.toString(arr));
    System.out.println(finalScore);
    }
    reader.close();
	writer.close();
}
}