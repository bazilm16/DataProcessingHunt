
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mupisiri
 * This program reads the data from two json files, 
 * parses them and answers the questions asked which 
 * are displayed on the output of the program. 
 * The data are records of two states Iowa and New York
 */
public class DataProcessingHunt {
	
	private List<String> cityData = new LinkedList<>();
	
	//this method loads the file to be read
	public void loadDataSet(String filename) throws IOException{
		Scanner str = new Scanner(new File(filename));
		while(str.hasNextLine()){
			this.cityData.add(str.nextLine());
		}
		str.close();
	}
	
	

	public static void main(String[] args) throws IOException {
		DataProcessingHunt data = new DataProcessingHunt();
		
		/*load the dataset from the file*/
		data.loadDataSet("dataset/iaData.json");
		
		/* *The data was available free from url: 
		 * 'http://api.sba.gov/geodata/city_data_for_state_of/ia.json' **/
		
		Stream<String> stream = (data.cityData.stream());
		String s = stream.map(records -> records.toLowerCase()).collect(Collectors.toList()).get(0);
		
		JSONParser parser = new JSONParser();
		
		
		try{
	         Object obj1 = parser.parse(s);
	         JSONArray array = (JSONArray)obj1;
	         int size  = array.size();
	         
	         Object city;
	         JSONObject obj2;
	         
	         /**Question 1**/
	         System.out.println("How many cities are there in Iowa?");
	         System.out.println("  > " + size);
	         System.out.println();
	         
	         /**Question 2**/
	         System.out.println("What are the co-ordinates for the location of Burlington?");
	         city = array.get(22);
	         obj2 = (JSONObject)city;
	         System.out.println("  > " + obj2.get("primary_latitude") + "degrees latitude and " +
	         obj2.get("primary_longitude") + "degrees longitude.");
	         System.out.println();
	         
	         /**Question 3**/
	         System.out.println("What is the full county name for Urbandale?");
	         city = array.get(28);
	         obj2 = (JSONObject)city;
	         System.out.println("  > " + obj2.get("full_county_name"));
	         System.out.println();
	         
	         /**Question 4**/
	         System.out.println("What is the web adress for the Grinell city?");
	         city = array.get(14);
	         obj2 = (JSONObject)city;
	         System.out.println("  > " + obj2.get("url"));
	         System.out.println();
	         
	         /**Question 5**/
	         System.out.println("What are the co-ordinates for the location of Fort Dodge?");
	         city = array.get(14);
	         obj2 = (JSONObject)city;
	         System.out.println("  > " + obj2.get("primary_latitude") + "degrees latitude and " +
	         obj2.get("primary_longitude") + "degrees longitude.");
	         System.out.println();
	         
	         /**Question 6**/
	         System.out.println("Is Manchester populated or not?");
	         city = array.get(132);
	         obj2 = (JSONObject)city;
	         System.out.println("  > It is a " + obj2.get("feat_class"));
	         
	         /**Question 7**/
	         System.out.println("List all the cities in Iowa?");
	         for(int x = 0; x<size; x++){
		         city = array.get(x);
		         obj2 = (JSONObject)city;
		         System.out.println("  > " + obj2.get("name"));
	         }
	         System.out.println();
	         
	    }catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
		}
		
		//clear the list so that we can add another dataset
		data.cityData.clear();
		
		//load the dataset for the state of New York
		data.loadDataSet("dataset/nyData.json");
		
		stream = (data.cityData.stream());
		s = stream.map(records -> records.toLowerCase()).collect(Collectors.toList()).get(0);
		
		parser = new JSONParser();
		
		try{
	         Object obj1 = parser.parse(s);
	         JSONArray array = (JSONArray)obj1;
	         int size  = array.size();
	         
	         Object city;
	         JSONObject obj2;
	         
	         /**Question 8**/
	         System.out.println("How many cities are there in the state of New York?");
	         System.out.println("  > " + size);
	         System.out.println();
	         
	         /**Question 9**/
	         System.out.println("In which county do we find Niagara falls?");
	         city = array.get(163);
	         obj2 = (JSONObject)city;
	         System.out.println("  > " + obj2.get("full_county_name"));
	         
	    }catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
		}
		
	}

}
