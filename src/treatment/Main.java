package treatment;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Main {

	static String fileLocation ="/home/mehdi/Bureau/simulators/Cloud/index.json";
	/**
	 * This is the main method which makes use of the methods.
	 * 
	 * @param args
	 *            Unused.
	 * @return Nothing.
	 * @exception IOException
	 *                On input error.
	 * @exception FileNotFoundException
	 *                On input error.
	 * @exception ParseException
	 *                On input error.
	 * @see IOException
	 * @see ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {

		// DataTransfer.SommeDataTransfert("Asia Pacific (Tokyo)", 100, 100,
		// 100,
		// 100);
		Price.getInstancePrice("US East (N. Virginia)", "Linux", 1, 3.75, 1, "2017-06-02T14:08:09", "2017-06-02T14:08:09");
		

	}

}
