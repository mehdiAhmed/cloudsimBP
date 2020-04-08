package treatment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

/**
 * The Price program implements a methods that Prints the price of an instance
 * in the three type and print it on the screen.
 * 
 * 
 * @author Imen NEJI and Slim KALLEL
 * @version 1.0
 * @since 2017-04-26
 */
public class Price {

	/**
	 * The getInstancePrice method is used to get the price of the instance in
	 * the 3 types
	 * 
	 * @param location
	 *            we have to find instance in a specific location
	 * @param os
	 *            we have to find instances with a specific operating system
	 * @param dcpu
	 *            we have to find the instances that have cpu=dcpu or just
	 *            superior .
	 * @param memory
	 *            we have to find the instances that have memory=dmemory .
	 * @param hour
	 *            the number of hour
	 * @param object
	 *            this is the sku of the shared product .
	 * @return float this returns price of the object
	 */
	public static void getInstancePrice(String location, String os, int dcpu,
			double d, int hour, String start, String end, String strateg) throws IOException, ParseException {
		//d=d/1024; //mo to gega
		System.out.println("location: " + location+", os:"+os+", dcpu: "+dcpu+", d:"+d+", hour:"+hour);
		ArrayList<Object> list = OnDemandTreatment.getInformationInstanceLoS(location, os, dcpu, d, hour);
		String s="";
		//System.out.println("The instance: " + list.get(0));
		if(strateg.equals("on-demand")){
			float prix = OnDemandTreatment.OnDemandInstancePrice(list.get(3));
			System.out.println("\n--- Price On-Demand : " + prix+"$");
			s="\n--- Price On-Demand : " + prix+"$";
		}
		else if(strateg.equals("Reserved")){
			System.out.println("\n--- Price Reserved : ");
			s+="\n--- Price Reserved : ";
			s+=ReservedTreatment.getInstancePriceReserved(list.get(3));
			//writeInFile(s);
		}
		else if(strateg.equals("Spot")){
			System.out.println("\n---Price SpotHistory : ");

			//start and end format: 2017-06-02T14:08:09
			SpotHistory.RunSpotPrice(list.get(0).toString(), start, end);
		}
		else {
			System.err.print("Strategy not exists !");
		}

	}

	public static void getInstancePrice(String location, String os, int dcpu,
			double d, int hour, String start, String end) throws IOException, ParseException {
		//d=d/1024; //mo to gega
		//System.out.println("location: " + location+", os:"+os+", dcpu: "+dcpu+", d:"+d+", hour:"+hour);
		ArrayList<Object> list = OnDemandTreatment.getInformationInstanceLoS(location, os, dcpu, d, hour);
		String s="";
		//System.out.println("The instance: " + list.get(0));
		float prix = OnDemandTreatment.OnDemandInstancePrice(list.get(3));
		System.out.println("\n--- Price On-Demand : " + prix+"$");
		s="\n--- Price On-Demand : " + prix+"$";

		System.out.println("\n--- Price Reserved : ");
		s+="\n--- Price Reserved : ";
		s+=ReservedTreatment.getInstancePriceReserved(list.get(3));
		//writeInFile(s);

		System.out.println("\n---Price SpotHistory : ");

		//start and end format: 2017-06-02T14:08:09
		SpotHistory.RunSpotPrice(list.get(0).toString(), start, end);

	}
	public static void writeInFile(String s) {

		try(FileWriter fw = new FileWriter("myfile.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(s);

			BufferedReader br = new BufferedReader(new FileReader("SpotInstance.json"));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				String everything = sb.toString();
				out.println(everything);
			} finally {
				br.close();
			}




		} catch (IOException e) {
			//exception handling left as an exercise for the reader
		}
	}

}
