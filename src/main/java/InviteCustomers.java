import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InviteCustomers {
    private static final int EARTH_RADIUS= 6371;
    private static final double DUBLIN_LAT= Math.toRadians(53.3381985);
    private static final double DUBLIN_LONG= Math.toRadians(-6.2592576);
    private static final int WITHIN_DISTANCE = 100;

 /*
  * 
  * 
    public static void main(String[] args) throws IOException, ParseException {
   	InputStream input = new URL("https://s3.amazonaws.com/intercom-take-home-test/customers.txt").openStream();
    	BufferedReader buf = new BufferedReader(new InputStreamReader(input));
    	        
    	String line = buf.readLine();
    	StringBuilder sb = new StringBuilder();
    	        
    	while(line != null){
    	   sb.append(line).append("\n");
    	   line = buf.readLine();
    	}
    	        
    	String fileAsString = sb.toString();
    	//buf.close();
    	//input.close();
        List<Customer> selected = selectCustomers(fileAsString);
        for (Customer c: selected){
            System.out.println("Name:"+c.getName()+" , "+" userId:"+c.getUserId());
        }
    }
*/
    // Having issue with the name too long for this reason i downloaded the file and created as Json
    	 public static void main(String[] args) throws IOException, ParseException {
    	  String path = "/Users/fmalagrino/Documents/GPSProblem/src/main/resources/customers.json";
          List<Customer> selected = selectCustomers(path);
          for (Customer c: selected){
              System.out.println("Name:"+c.getName()+" , "+" userId:"+c.getUserId());
          }
  }
    /**
     * Select the customers that are within 100 km from Dublin Office
     * @return List of selected customers
     * @throws IOException
     * @throws ParseException
     */
     static List<Customer> selectCustomers(String path) throws IOException, ParseException {
        List<Customer> selectedCustomers = new ArrayList<Customer>();
        JSONParser jsonParser = new JSONParser();
        JSONArray customers = (JSONArray)jsonParser.parse(new FileReader(path));
        for (Object object: customers){
            JSONObject customer = (JSONObject) object;
            double latitude = new Double(customer.get("latitude").toString());
            double longitude = new Double(customer.get("longitude").toString());

            if (calculateDistance(Math.toRadians(latitude),Math.toRadians(longitude)) <= WITHIN_DISTANCE){
                String name = customer.get("name").toString();
                int userId = Integer.parseInt(customer.get("user_id").toString());
                selectedCustomers.add(new Customer(name,userId));
            }
        }
         Collections.sort(selectedCustomers);
        return selectedCustomers;
    }

    /**
     * Calculate distance between the Dublin Office and the customer's location
     * @param latitudeInRadian customer's latitude in radian
     * @param longitudeInRadian customer's longitude in radian
     * @return double value giving the distance
     */
     static double calculateDistance(double latitudeInRadian,double longitudeInRadian){
        double deltaLongitude = DUBLIN_LONG - longitudeInRadian;
        double centralAngle = Math.acos((Math.sin(DUBLIN_LAT) * Math.sin(latitudeInRadian)) +
                (Math.cos(DUBLIN_LAT) * Math.cos(latitudeInRadian) * Math.cos(deltaLongitude)));
        return EARTH_RADIUS*centralAngle;
    }

}
